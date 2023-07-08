package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TraineeContactInfoDao;
import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.exception.*;
import com.lukefitness.lukegymbackend.exception.badrequest.EmailAlreadyExistsException;
import com.lukefitness.lukegymbackend.exception.badrequest.KeywordCannotBeNullException;
import com.lukefitness.lukegymbackend.exception.badrequest.UserAlreadyExistsException;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraineeServiceImp implements TraineeService {
    @Autowired
    TraineeDao traineeDao;
    @Autowired
    TraineeContactInfoDao traineeContactInfoDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Trainee traineeLogin(String username, String password) throws Exception {
        Trainee traineeGetByUsername = getTraineeByUsername(username);
        String pwdInDB = traineeGetByUsername.getPassword();
        if(!passwordEncoder.matches(password,pwdInDB)){
            throw new LoginFailException();
        }else {
            return traineeGetByUsername;
        }
    }

    @Override
    public Trainee getTraineeByUsername(String username){
        Trainee trainee=traineeDao.getTraineeByUsername(username);
        if(trainee==null){
            throw new NotFoundException("Username not found");
        }else{
            return trainee;
        }
    }

    @Transactional
    @Override
    public Trainee traineeRegister(Trainee trainee) {
        try{
            trainee.setPassword(passwordEncoder.encode(trainee.getPassword()));
            traineeDao.traineeRegister(trainee);
            traineeContactInfoDao.insertTraineeContactInfo(trainee.getId());
            return getTraineeByUsername(trainee.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            if (e.getMessage().contains("key 'username'")){
                throw new UserAlreadyExistsException();
            }else if (e.getMessage().contains("key 'email'")){
                throw new EmailAlreadyExistsException();
            }else if(e.getMessage().contains("null")){
                String keyword= ExceptionUtils.extractKeywordFromSQLException(e.getMessage());
                throw new KeywordCannotBeNullException(keyword);
            }else{
                throw e;
            }
        }
    }

    @Override
    public Trainee getTraineeById(int traineeId) {
        Trainee trainee=traineeDao.getTraineeById(traineeId);
        if (trainee==null){
            throw new NotFoundException("Trainee id not found");
        }else{
            return trainee;
        }
    }

    @Override
    public Trainee getTraineeByEmail(String email) {
        Trainee trainee=traineeDao.getTraineeByEmail(email);
        if (trainee==null){
            throw new NotFoundException("Trainee email not found");
        }else{
            return trainee;
        }
    }

    @Override
    public void updateTraineePassword(int id, String password) {
        Trainee trainee=traineeDao.getTraineeById(id);
        if (trainee==null){
            throw new NotFoundException("Trainee id not found");
        }else{
            trainee.setPassword(passwordEncoder.encode(password));
            traineeDao.updateTraineePassword(trainee);
        }
    }

    @Transactional
    @Override
    public void updateTraineeEmail(int id, String email) {
        Trainee trainee=traineeDao.getTraineeById(id);
        if (trainee==null){
            throw new NotFoundException("Trainee id not found");
        }else{
            trainee.setEmail(email);
            traineeDao.setEmailUnverified(id);
            traineeDao.updateTraineeEmail(trainee);
        }
    }
}
