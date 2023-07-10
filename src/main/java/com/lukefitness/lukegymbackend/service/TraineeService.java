package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.request.register.UserRegisterReq;
import com.lukefitness.lukegymbackend.models.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.models.response.login.TraineeLoginResponse;

import java.util.List;

public interface TraineeService {
    TraineeLoginResponse traineeLogin(String username, String password);
    TraineeLoginResponse traineeLoginByEmail(String email, String password);
    Trainee getTraineeByUsername(String username);
    TraineeResponse traineeRegister(UserRegisterReq trainee);

    Trainee getTraineeById(int traineeId);
    Trainee getTraineeByEmail(String email);
    void updateTraineePassword(int id, String password);
    void updateTraineeEmail(int id, String email);
    void deactivateTrainee(int traineeId);
}
