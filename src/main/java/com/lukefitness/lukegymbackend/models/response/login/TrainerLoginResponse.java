package com.lukefitness.lukegymbackend.models.response.login;

import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.models.response.register.TrainerResponse;
import com.lukefitness.lukegymbackend.utils.JWTUtils;

public class TrainerLoginResponse extends TrainerResponse {
    String token;

    public TrainerLoginResponse(Trainer trainer){
        super(trainer);
        this.token= JWTUtils.getToken(trainer);
    }
}
