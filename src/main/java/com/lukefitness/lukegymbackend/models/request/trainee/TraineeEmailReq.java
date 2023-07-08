package com.lukefitness.lukegymbackend.models.request.trainee;

import com.lukefitness.lukegymbackend.models.request.Email;

public class TraineeEmailReq extends Email {
    private String traineeId;
    public TraineeEmailReq() {}
    public String getTraineeId() { return traineeId; }
    public void setTraineeId(String traineeId) { this.traineeId = traineeId; }
}
