package com.lukefitness.lukegymbackend.models.request.trainee;

import com.lukefitness.lukegymbackend.models.request.Password;

public class TraineePwReq extends Password {
    private String traineeId;
    public TraineePwReq() {}
    public String getTraineeId() { return traineeId; }
    public void setTraineeId(String traineeId) { this.traineeId = traineeId; }
}
