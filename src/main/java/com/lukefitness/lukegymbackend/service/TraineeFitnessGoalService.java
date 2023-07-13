package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.TraineeFitnessGoal;
import org.apache.ibatis.javassist.NotFoundException;

public interface TraineeFitnessGoalService {
    public TraineeFitnessGoal getTraineeFitnessGoal(int traineeId) throws NotFoundException;
    public void updateTraineeFitnessGoal(TraineeFitnessGoal traineeFitnessGoal) throws NotFoundException;
}
