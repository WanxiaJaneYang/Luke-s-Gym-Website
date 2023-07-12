package com.lukefitness.lukegymbackend.dto.response.login;

import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeLoginResponse extends TraineeResponse {
    String token;

    public TraineeLoginResponse(Trainee trainee){
        super(trainee);
        this.token= JWTUtils.getToken(trainee);
    }
}
