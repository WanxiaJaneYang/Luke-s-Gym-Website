package com.lukefitness.lukegymbackend.models.request.trainer;

import com.lukefitness.lukegymbackend.models.request.Email;

public class TrainerEmailReq extends Email {
    private String trainerId;
    public TrainerEmailReq() {}
    public String getTrainerId() { return trainerId; }
    public void setTrainerId(String trainerId) { this.trainerId = trainerId; }
}
