package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.Trainee;

import java.util.List;

public interface TraineeService {
    Trainee traineeLogin(String username, String password);
    Trainee getTraineeByUsername(String username);
    Trainee traineeRegister(Trainee trainee);

    Trainee getTraineeById(int traineeId);
    Trainee getTraineeByEmail(String email);
    void updateTraineePassword(int id, String password);
    void updateTraineeEmail(int id, String email);

    List<Trainee> getTraineesByPage(int page, int size);
    List<Trainee> getTraineesBySearchUsername(String username, int page, int size);
    List<Trainee> getTraineesBySearchEmail(String email, int page, int size);
}
