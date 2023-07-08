package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.Trainee;

public interface TraineeService {
    Trainee traineeLogin(String username, String password);
    Trainee getTraineeByUsername(String username);
    Trainee traineeRegister(Trainee trainee);

    Trainee getTraineeById(int traineeId);
    Trainee getTraineeByEmail(String email);
    void updateTraineePassword(int id, String password);
    void updateTraineeEmail(int id, String email);
}
