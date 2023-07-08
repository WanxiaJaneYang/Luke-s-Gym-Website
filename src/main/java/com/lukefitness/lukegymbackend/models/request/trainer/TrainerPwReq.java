package com.lukefitness.lukegymbackend.models.request.trainer;

import com.lukefitness.lukegymbackend.models.request.Password;

public class TrainerPwReq extends Password {
    private String trainerId;
    public TrainerPwReq() {}
    public String getTrainerId() { return trainerId; }
    public void setTrainerId(String trainerId) { this.trainerId = trainerId; }
}
