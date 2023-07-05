package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.exception.*;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImp implements TraineeService {
    @Autowired
    TraineeDao traineeDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Trainee traineeLogin(String username, String password) {
        Trainee traineeGetByUsername = getTraineeByUsername(username);
        String pwdInDB = traineeGetByUsername.getPassword();
        String hashedPassword=passwordEncoder.encode(password);
        if(!passwordEncoder.matches(password,pwdInDB)){
            throw new LoginFailException();
        }else {
            return traineeGetByUsername;
        }
    }

    @Override
    public Trainee getTraineeByUsername(String username) {
        Trainee trainee=traineeDao.getTraineeByUsername(username);
        if(trainee==null){
            throw new UserNotExistException();
        }else{
            return trainee;
        }
    }

    @Override
    public Trainee traineeRegister(Trainee trainee) throws Exception {
        try{
            trainee.setPassword(passwordEncoder.encode(trainee.getPassword()));
            traineeDao.traineeRegister(trainee);
            return getTraineeByUsername(trainee.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            if (e.getMessage().contains("key 'username'")){
                throw new UserAlreadyExistsException();
            }else if (e.getMessage().contains("key 'email'")){
                throw new EmailAlreadyExistsException();
            }else if(e.getMessage().contains("null")){
                throw new KeywordCannotBeNullException(e.getMessage());
            }else{
                throw e;
            }
        }
    }
}
