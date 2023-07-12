package com.lukefitness.lukegymbackend.models;

import com.lukefitness.lukegymbackend.dto.request.register.UserRegisterReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainee extends User{
    int trainer_id;

    public Trainee(UserRegisterReq traineeRegisterReq){
        super(traineeRegisterReq.getUsername(), traineeRegisterReq.getPassword(), traineeRegisterReq.getEmail());
        this.email_verified = false;
    }
}
