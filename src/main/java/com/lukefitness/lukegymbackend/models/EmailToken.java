package com.lukefitness.lukegymbackend.models;

import com.lukefitness.lukegymbackend.utils.EmailTokenGenerator;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmailToken {
    private int id;
    private int userId;
    private String username;
    private String token;
    private String userType;
    private Timestamp expirationDate;

    public EmailToken(){

    }

    public  EmailToken(Trainer trainer){
        this.userId=trainer.getId();
        this.username=trainer.getUsername();
        this.token=EmailTokenGenerator.generate();
        this.userType="trainer";
        this.expirationDate=EmailTokenGenerator.getExpirationDate();

    }

    public EmailToken(Trainee trainee){
        this.userId=trainee.getId();
        this.username=trainee.getUsername();
        this.token=EmailTokenGenerator.generate();
        this.userType="trainee";
        this.expirationDate=EmailTokenGenerator.getExpirationDate();
    }

}
