package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.TraineeHealthMetric;
import org.apache.ibatis.javassist.NotFoundException;

public interface TraineeHealthMetricService {
    TraineeHealthMetric getTraineeHealthMetric(Integer traineeId) throws NotFoundException;
    void updateTraineeHealthMetric(TraineeHealthMetric traineeHealthMetric) throws NotFoundException;
}
