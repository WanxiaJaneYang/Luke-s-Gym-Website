package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TraineeContactInfoDao;
import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.dao.TraineeFitnessGoalDao;
import com.lukefitness.lukegymbackend.dao.TraineeHealthMetricDao;
import com.lukefitness.lukegymbackend.exception.*;
import com.lukefitness.lukegymbackend.exception.badrequest.EmailAlreadyExistsException;
import com.lukefitness.lukegymbackend.exception.badrequest.KeywordCannotBeNullException;
import com.lukefitness.lukegymbackend.exception.badrequest.UserAlreadyExistsException;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.dto.request.register.UserRegisterReq;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.dto.response.login.TraineeLoginResponse;
import com.lukefitness.lukegymbackend.models.TraineeFitnessGoal;
import com.lukefitness.lukegymbackend.models.TraineeHealthMetric;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class TraineeServiceImp implements TraineeService {
    @Autowired
    TraineeDao traineeDao;
    @Autowired
    TraineeContactInfoDao traineeContactInfoDao;

    @Autowired
    TraineeFitnessGoalDao traineeFitnessGoalDao;

    @Autowired
    TraineeHealthMetricDao traineeHealthMetricDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public TraineeLoginResponse traineeLogin(String username, String password) {
        Trainee traineeGetByUsername = getTraineeByUsername(username);
        String pwdInDB = traineeGetByUsername.getPassword();
        if(!passwordEncoder.matches(password,pwdInDB)){
            throw new LoginFailException();
        }else {
            if(!traineeGetByUsername.is_active())
                throw new UnauthorizedException("Account deactivated");
            traineeDao.traineeLogin(traineeGetByUsername);
            return new TraineeLoginResponse(traineeGetByUsername);
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
    public TraineeResponse traineeRegister(UserRegisterReq traineeRegisterReq) {
        try{
            traineeRegisterReq.setPassword(passwordEncoder.encode(traineeRegisterReq.getPassword()));
            Trainee trainee=new Trainee(traineeRegisterReq);
            traineeDao.traineeRegister(trainee);
            traineeContactInfoDao.insertTraineeContactInfo(trainee.getId());
            traineeFitnessGoalDao.insert(new TraineeFitnessGoal(trainee.getId()));
            traineeHealthMetricDao.insert(new TraineeHealthMetric(trainee.getId()));
            return new TraineeResponse(trainee);
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

    @Override
    public void deactivateTrainee(int traineeId) {
        traineeDao.deactivateTrainee(traineeId);
    }

    @Override
    public void linkTraineeToTrainer(int traineeId, int trainerId) {
        int affectedRow=traineeDao.linkTraineeToTrainer(traineeId,trainerId);
        if (affectedRow==0)
            throw new NotFoundException("Trainee id not found");
    }

    @Override
    public void unlinkTraineeToTrainer(int traineeId) {
        int affectedRow=traineeDao.unlinkTraineeToTrainer(traineeId);
        if (affectedRow==0)
            throw new NotFoundException("Trainee id not found");
    }

    @Transactional
    @Override
    public TraineeLoginResponse traineeLoginByEmail(String email, String password) {
        Trainee traineeGetByEmail = getTraineeByEmail(email);
        String pwdInDB = traineeGetByEmail.getPassword();
        if(!passwordEncoder.matches(password,pwdInDB)){
            throw new LoginFailException();
        }else {
            if(!traineeGetByEmail.is_active())
                throw new UnauthorizedException("Account deactivated");
            traineeDao.traineeLogin(traineeGetByEmail);
            return new TraineeLoginResponse(traineeGetByEmail);
        }
    }
}
