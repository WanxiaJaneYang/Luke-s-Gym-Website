package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TrainerDao;
import com.lukefitness.lukegymbackend.exception.*;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImp implements TrainerService {

    @Autowired
    TrainerDao trainerDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Trainer registerTrainer(Trainer trainer) throws Exception {
        try{
            String hashedPassword=passwordEncoder.encode(trainer.getPassword());
            trainer.setPassword(hashedPassword);
            trainerDao.registerTrainer(trainer);
            return trainer;
        }catch (Exception e){
            e.printStackTrace();
            if (e.getMessage().contains("key 'username'")){
                throw new UserAlreadyExistsException();
            }else if (e.getMessage().contains("key 'email'")){
                throw new EmailAlreadyExistsException();
            }else if(e.getMessage().contains("null")){
                throw new KeywordCannotBeNullException(e.getMessage());
            }else{
                throw new Exception(e.getMessage());
            }
        }
    }

    @Override
    public Trainer getTrainerByUsername(String username) {
        Trainer trainer=trainerDao.getTrainerByName(username);
        if(trainer==null){
            throw new NotFoundException("Trainer username not found");
        }else{
            return trainer;
        }
    }

    @Override
    public Trainer trainerLogin(String username, String password) throws Exception {
        Trainer trainerGetByName = trainerDao.getTrainerByName(username);
        if(trainerGetByName==null){
            throw new NotFoundException("Trainer username not found");
        }else{
            String pwdInDB = trainerGetByName.getPassword();
            String hashedPassword=passwordEncoder.encode(password);
            if(!passwordEncoder.matches(password,pwdInDB)){
                throw new LoginFailException();
            }else{
                return trainerGetByName;
            }

        }
    }

    @Override
    public Trainer getTrainerById(int id) {
        Trainer trainer=trainerDao.getTrainerById(id);
        if(trainer==null){
            throw new NotFoundException("Trainer id not found");
        }else{
            return trainer;
        }
    }

    @Override
    public Trainer getTrainerByEmail(String email) {
        Trainer trainer=trainerDao.getTrainerByEmail(email);
        if(trainer==null){
            throw new NotFoundException("Trainer email not found");
        }else{
            return trainer;
        }
    }
}
