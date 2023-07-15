package com.lukefitness.lukegymbackend.dto.response.query;

import com.lukefitness.lukegymbackend.models.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserQueryResponse {
    int id;
    String username;
    String email;
    boolean email_verified;

    public SimpleUserQueryResponse(Trainer trainer){
        this.id=trainer.getId();
        this.username=trainer.getUsername();
        this.email=trainer.getEmail();
        this.email_verified=trainer.isEmail_verified();
    }
}
