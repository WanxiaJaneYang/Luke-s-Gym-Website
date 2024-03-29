package com.lukefitness.lukegymbackend.dto.response.login;

import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.dto.response.register.TrainerResponse;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerLoginResponse extends TrainerResponse {
    String accessToken;
    String refreshToken;
    public TrainerLoginResponse(Trainer trainer){
        super(trainer);
        this.accessToken= JWTUtils.getToken(trainer);
        this.refreshToken=JWTUtils.getRefreshToken(trainer);
    }
}
