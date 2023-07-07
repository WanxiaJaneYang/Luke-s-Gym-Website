package com.lukefitness.lukegymbackend.models;

import com.lukefitness.lukegymbackend.utils.EmailTokenGenerator;
import lombok.Data;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Data
public class EmailToken {
    private int id;
    private int user_id;
    private String username;
    private String token;
    private String user_type;
    private Timestamp expiration_date;

    public EmailToken(){

    }

    public  EmailToken(User user){
        this.user_id=user.getId();
        this.username=user.getUsername();
        this.token=EmailTokenGenerator.generate();
        this.expiration_date=EmailTokenGenerator.getExpirationDate();
        if (user instanceof Trainer){
            this.user_type="trainer";
        }else if(user instanceof Trainee){
            this.user_type="trainee";
        }
    }
}
