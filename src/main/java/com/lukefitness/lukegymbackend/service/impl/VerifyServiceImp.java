package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.EmailTokenDao;
import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.dao.TrainerDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.EmailToken;
import com.lukefitness.lukegymbackend.service.VerifyService;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
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
    public Map<String, Object> verifyEmail(int tokenId, String token) {
        EmailToken tokenRecord=verifyToken(tokenId, token);
        if(tokenRecord.getUser_type().equals("trainer")){
            trainerDao.setEmailVerified(tokenRecord.getUser_id());
        }else if(tokenRecord.getUser_type().equals("trainee")) {
            traineeDao.setEmailVerified(tokenRecord.getUser_id());
        }
        Map<String, Object> map;
        if (tokenRecord.getUser_type().equals("trainer")) {
            map= Map.of("userType", "trainer", "id", tokenRecord.getUser_id());
        } else if (tokenRecord.getUser_type().equals("trainee")) {
            map= Map.of("userType", "trainee", "id", tokenRecord.getUser_id());
        } else if(tokenRecord.getUser_type().equals("admin")){
            map= Map.of("userType", "admin", "id", tokenRecord.getUser_id());
        }else {
            throw new BadRequestException("Invalid token");
        }
        String loginToken= JWTUtils.getToken(String.valueOf(tokenRecord.getUser_id()),tokenRecord.getUsername(),tokenRecord.getUser_type());
        map.put("token",loginToken);
        return map;
    }

    @Override
    public Map<String, Object> verifyResetPw(int tokenId, String token) {
        EmailToken tokenRecord = verifyToken(tokenId, token);
        Map<String, Object> map;
        if (tokenRecord.getUser_type().equals("trainer")) {
            map= Map.of("userType", "trainer", "id", tokenRecord.getUser_id());
        } else if (tokenRecord.getUser_type().equals("trainee")) {
            map= Map.of("userType", "trainee", "id", tokenRecord.getUser_id());
        } else if(tokenRecord.getUser_type().equals("admin")){
            map= Map.of("userType", "admin", "id", tokenRecord.getUser_id());
        }else {
            throw new BadRequestException("Invalid token");
        }
        String loginToken= JWTUtils.getToken(String.valueOf(tokenRecord.getUser_id()),tokenRecord.getUsername(),tokenRecord.getUser_type());
        map.put("token",loginToken);
        return map;
    }
}
