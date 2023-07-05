package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.TraineeContactInfo;
import org.springframework.stereotype.Service;

public interface TraineeContactInfoService {
    TraineeContactInfo updateTraineeContact(int traineeId, TraineeContactInfo traineeContactInfo) throws Exception;
    TraineeContactInfo getTraineeContactInfoByTraineeId(int traineeId) throws BadRequestException, Exception;
}
