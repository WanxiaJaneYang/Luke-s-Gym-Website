package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.Trainee;

public interface TraineeService {
    Trainee traineeLogin(String username, String password);
    Trainee getTraineeByUsername(String username);
    Trainee traineeRegister(Trainee trainee) throws Exception;
}
