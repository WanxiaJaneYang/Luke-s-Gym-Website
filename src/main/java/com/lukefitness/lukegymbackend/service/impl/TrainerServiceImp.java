package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TrainerDao;
import com.lukefitness.lukegymbackend.dto.response.query.SimpleUserQueryResponse;
import com.lukefitness.lukegymbackend.exception.*;
import com.lukefitness.lukegymbackend.exception.badrequest.EmailAlreadyExistsException;
import com.lukefitness.lukegymbackend.exception.badrequest.KeywordCannotBeNullException;
import com.lukefitness.lukegymbackend.exception.badrequest.UserAlreadyExistsException;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.dto.request.register.TrainerRegisterReq;
import com.lukefitness.lukegymbackend.dto.response.login.TrainerLoginResponse;
import com.lukefitness.lukegymbackend.dto.response.register.TrainerResponse;
import com.lukefitness.lukegymbackend.service.TrainerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TrainerServiceImp implements TrainerService {

    @Autowired
    TrainerDao trainerDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public TrainerResponse registerTrainer(TrainerRegisterReq trainerRegisterReq) {
        try{
            String hashedPassword=passwordEncoder.encode(trainerRegisterReq.getPassword());
            trainerRegisterReq.setPassword(hashedPassword);
            Trainer trainer=new Trainer(trainerRegisterReq);
            trainerDao.registerTrainer(trainer);
            return new TrainerResponse(trainer);
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

    @Override
    public Trainer getTrainerByUsername(String username) {
        Trainer trainer=trainerDao.getTrainerByName(username);
        if(trainer==null){
            throw new NotFoundException("Trainer username not found");
        }else{
            return trainer;
        }
    }

    @Transactional
    @Override
    public TrainerLoginResponse trainerLogin(String username, String password){
        Trainer trainerGetByUsername = trainerDao.getTrainerByName(username);
        if(trainerGetByUsername==null){
            throw new NotFoundException("Trainer username not found");
        }else{
            if(!trainerGetByUsername.is_active())
                throw new UnauthorizedException("Account deactivated");
            String pwdInDB = trainerGetByUsername.getPassword();
            if(!passwordEncoder.matches(password,pwdInDB)){
                throw new LoginFailException();
            }else{
                trainerDao.trainerLogin(trainerGetByUsername);
                return new TrainerLoginResponse(trainerGetByUsername);
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

    @Override
    public void updateTrainerPassword(int id, String password) {
        Trainer trainer=trainerDao.getTrainerById(id);
        if(trainer==null){
            throw new NotFoundException("Trainer id not found");
        }else{
            String hashedPassword=passwordEncoder.encode(password);
            trainer.setPassword(hashedPassword);
            trainerDao.updateTrainerPassword(trainer);
        }
    }

    @Transactional
    @Override
    public void updateTrainerEmail(int id, String email) {
        Trainer trainer=trainerDao.getTrainerById(id);
        if(trainer==null){
            throw new NotFoundException("Trainer id not found");
        }else{
            trainer.setEmail(email);
            trainerDao.updateTrainerEmail(trainer);
            trainerDao.setEmailUnverified(id);
        }
    }

    @Override
    public void updateTrainerUsername(int id, String username) {
        int affectedRows=trainerDao.updateTrainerUsername(id,username);
        if(affectedRows==0){
            throw new NotFoundException("Trainer id not found");
        }
    }

    @Transactional
    @Override
    public TrainerLoginResponse trainerLoginByEmail(String email, String password) {
        Trainer trainerGetByEmail = trainerDao.getTrainerByEmail(email);
        if(trainerGetByEmail==null){
            throw new NotFoundException("Trainer email not found");
        }else{
            if(!trainerGetByEmail.is_active())
                throw new UnauthorizedException("Account deactivated");
            String pwdInDB = trainerGetByEmail.getPassword();
            if(!passwordEncoder.matches(password,pwdInDB)){
                throw new LoginFailException();
            }else{
                trainerDao.trainerLogin(trainerGetByEmail);
                return new TrainerLoginResponse(trainerGetByEmail);
            }
        }
    }

    @Override
    public void deactivateTrainer(int trainerId) {
        int affectedRows=trainerDao.setDeactivationDate(trainerId);
        if(affectedRows==0){
            throw new NotFoundException("Trainer id not found");
        }
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerDao.getAllTrainers();
    }

    @Override
    public List<Trainer> getTrainers(int page, int size) {
        int offset=(page-1)*size;
        RowBounds rowBounds=new RowBounds(offset,size);
        return trainerDao.getTrainers(rowBounds);
    }

    @Override
    public List<Trainer> searchTrainerByUsername(String username, int page, int size) {
        int offset=(page-1)*size;
        RowBounds rowBounds=new RowBounds(offset,size);
        return trainerDao.searchTrainerByUsername(username,rowBounds);
    }

    @Override
    public List<Trainer> searchTrainerByEmail(String email, int page, int size) {
        int offset=(page-1)*size;
        RowBounds rowBounds=new RowBounds(offset,size);
        return trainerDao.searchTrainerByEmail(email,rowBounds);
    }
}
