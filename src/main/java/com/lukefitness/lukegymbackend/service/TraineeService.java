package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.UserNotExistException;
import com.lukefitness.lukegymbackend.models.Trainee;

public interface TraineeService {
    Trainee traineeLogin(String username, String password) throws Exception;
    Trainee getTraineeByUsername(String username) throws UserNotExistException, BadRequestException, Exception;
    Trainee traineeRegister(Trainee trainee) throws Exception;

    Trainee getTraineeById(int traineeId);
    Trainee getTraineeByEmail(String email);
    void updateTraineePassword(int id, String password);
    void updateTraineeEmail(int id, String email);
}
