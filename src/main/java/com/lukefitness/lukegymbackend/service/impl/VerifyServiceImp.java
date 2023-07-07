package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.EmailTokenDao;
import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.dao.TrainerDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.EmailToken;
import com.lukefitness.lukegymbackend.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Service
public class VerifyServiceImp implements VerifyService {
    @Autowired
    EmailTokenDao emailTokenDao;

    @Transactional
    public EmailToken verifyToken(int tokenId, String token) {
        EmailToken emailToken = new EmailToken();
        emailToken.setId(tokenId);
        emailToken.setToken(token);
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

    @Autowired
    TrainerDao trainerDao;

    @Autowired
    TraineeDao traineeDao;
    @Transactional
    @Override
    public void verifyEmail(int tokenId, String token) {
        EmailToken tokenRecord=verifyToken(tokenId, token);
        if(tokenRecord.getUser_type().equals("trainer")){
            trainerDao.setEmailVerified(tokenRecord.getUser_id());
        }else if(tokenRecord.getUser_type().equals("trainee")) {
            trainerDao.setEmailVerified(tokenRecord.getUser_id());
        }
    }

    @Override
    public Map<String, Object> verifyResetPw(int tokenId, String token) {
        EmailToken tokenRecord = verifyToken(tokenId, token);
        if (tokenRecord.getUser_type().equals("trainer")) {
            return Map.of("userType", "trainer", "id", tokenRecord.getUser_id());
        } else if (tokenRecord.getUser_type().equals("trainee")) {
            return Map.of("userType", "trainee", "id", tokenRecord.getUser_id());
        } else {
            throw new BadRequestException("Invalid token");
        }
    }
}
