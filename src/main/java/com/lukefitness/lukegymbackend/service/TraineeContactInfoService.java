package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.TraineeContactInfo;

public interface TraineeContactInfoService {
    void updateTraineeContact(int traineeId, TraineeContactInfo traineeContactInfo) throws Exception;
    TraineeContactInfo getTraineeContactInfoByTraineeId(int traineeId) throws Exception;
}
