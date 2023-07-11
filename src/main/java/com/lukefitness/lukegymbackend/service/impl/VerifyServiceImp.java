package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.EmailTokenDao;
import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.dao.TrainerDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.EmailToken;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.models.User;
import com.lukefitness.lukegymbackend.models.response.login.TraineeLoginResponse;
import com.lukefitness.lukegymbackend.models.response.login.TrainerLoginResponse;
import com.lukefitness.lukegymbackend.models.response.register.UserResponse;
import com.lukefitness.lukegymbackend.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class VerifyServiceImp implements VerifyService {
    @Autowired
    EmailTokenDao emailTokenDao;

    @Transactional
    public EmailToken verifyToken(int tokenId, String token) {
        EmailToken emailToken = new EmailToken(tokenId, token);
        EmailToken emailTokenDB = emailTokenDao.getEmailToken(emailToken);
        if (emailTokenDB == null) {
            throw new BadRequestException("Invalid token");
        }

        Timestamp utcNow = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
        Timestamp expirationDate = emailTokenDB.getExpiration_date();

        if(utcNow.after(expirationDate)){
            throw new BadRequestException("Token expired");
        }else{
            emailTokenDao.deleteEmailTokenById(tokenId);
            return emailTokenDB;
        }
    }

    private UserResponse getUserResponse(EmailToken tokenRecord){
        User user;
        if (tokenRecord.getUser_type().equals("trainer")||tokenRecord.getUser_type().equals("admin")){
            user=trainerDao.getTrainerById(tokenRecord.getUser_id());
        }else if (tokenRecord.getUser_type().equals("trainee")){
            user=traineeDao.getTraineeById(tokenRecord.getUser_id());
        }else {
            throw new BadRequestException("Invalid token: user type not found");
        }

        if(user instanceof Trainee){
            return new TraineeLoginResponse((Trainee) user);
        }else {
            return new TrainerLoginResponse((Trainer) user);
        }
    }
    @Autowired
    TrainerDao trainerDao;

    @Autowired
    TraineeDao traineeDao;
    @Transactional
    @Override
    public UserResponse verifyEmail(int tokenId, String token) {
        EmailToken tokenRecord=verifyToken(tokenId, token);
        if(tokenRecord.getUser_type().equals("trainer")){
            trainerDao.setEmailVerified(tokenRecord.getUser_id());
        }else if(tokenRecord.getUser_type().equals("trainee")) {
            traineeDao.setEmailVerified(tokenRecord.getUser_id());
        }
        return getUserResponse(tokenRecord);
    }

    @Transactional
    @Override
    public UserResponse verifyResetPw(int tokenId, String token) {
        EmailToken tokenRecord = verifyToken(tokenId, token);
        return getUserResponse(tokenRecord);
    }
}
