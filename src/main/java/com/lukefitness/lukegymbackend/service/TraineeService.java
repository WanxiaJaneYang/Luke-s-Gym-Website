package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.response.TraineeResponse;

import java.util.List;

public interface TraineeService {
    Trainee traineeLogin(String username, String password);
    Trainee getTraineeByUsername(String username);
    Trainee traineeRegister(Trainee trainee);

    Trainee getTraineeById(int traineeId);
    Trainee getTraineeByEmail(String email);
    void updateTraineePassword(int id, String password);
    void updateTraineeEmail(int id, String email);

    List<TraineeResponse> getTraineesByPage(int page, int size);
    List<TraineeResponse> getTraineesBySearchUsername(String username, int page, int size);
    List<TraineeResponse> getTraineesBySearchEmail(String email, int page, int size);
}
