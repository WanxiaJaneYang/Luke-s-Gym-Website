package com.lukefitness.lukegymbackend.models;

import com.lukefitness.lukegymbackend.dto.request.register.TrainerRegisterReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trainer extends User {
    private boolean is_admin;

    public Trainer(TrainerRegisterReq trainerRegisterReq){
        super(trainerRegisterReq.getUsername(), trainerRegisterReq.getPassword(), trainerRegisterReq.getEmail());
        this.is_admin = trainerRegisterReq.is_admin();
        this.email_verified = false;
    }
}
