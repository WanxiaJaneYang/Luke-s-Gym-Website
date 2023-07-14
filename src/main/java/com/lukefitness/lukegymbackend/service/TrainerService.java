package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.dto.response.query.SimpleUserQueryResponse;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.dto.request.register.TrainerRegisterReq;
import com.lukefitness.lukegymbackend.dto.response.login.TrainerLoginResponse;
import com.lukefitness.lukegymbackend.dto.response.register.TrainerResponse;

import java.util.List;

public interface TrainerService {
    TrainerResponse registerTrainer(TrainerRegisterReq trainerRegisterReq);
    Trainer getTrainerByUsername(String username);
    TrainerLoginResponse trainerLogin(String username, String password);
    Trainer getTrainerById(int id);
    Trainer getTrainerByEmail(String email);

    void updateTrainerPassword(int id, String password);
    void updateTrainerEmail(int id, String email);

    TrainerLoginResponse trainerLoginByEmail(String email, String password);
    void deactivateTrainer(int trainerId);
    List<SimpleUserQueryResponse> getTrainers(int page, int size);
}
