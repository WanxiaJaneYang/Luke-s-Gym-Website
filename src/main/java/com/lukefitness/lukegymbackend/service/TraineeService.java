package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.dto.request.register.UserRegisterReq;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.dto.response.login.TraineeLoginResponse;

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
    void linkTraineeToTrainer(int traineeId, int trainerId);
    void unlinkTraineeToTrainer(int traineeId);

    PageInfo<TraineeResponse> getTraineesByTrainerId(int trainerId, int pageNumber, int pageSize);
}
