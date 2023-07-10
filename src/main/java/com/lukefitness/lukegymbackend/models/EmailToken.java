package com.lukefitness.lukegymbackend.models;

import com.lukefitness.lukegymbackend.utils.EmailTokenGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailToken {
    private int id;
    private int user_id;
    private String username;
    private String token;
    private String user_type;
    private Timestamp expiration_date;

    public  EmailToken(User user){
        this.user_id=user.getId();
        this.username=user.getUsername();
        this.token=EmailTokenGenerator.generate();
        this.expiration_date=EmailTokenGenerator.getExpirationDate();
        if (user instanceof Trainer){
            if (((Trainer) user).is_admin()){
                this.user_type="admin";
            }else {
                this.user_type="trainer";
            }
        }else if(user instanceof Trainee){
            this.user_type="trainee";
        }
    }

    public EmailToken(int tokenId, String token) {
        this.id = tokenId;
        this.token = token;
    }
}
