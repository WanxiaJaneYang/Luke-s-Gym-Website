package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.Trainer;

import java.util.Map;

public interface TrainerService {
    Trainer registerTrainer(Trainer trainer);
    Trainer getTrainerByUsername(String username);
    Map trainerLogin(String username, String password);
    Trainer getTrainerById(int id);
    Trainer getTrainerByEmail(String email);

    void updateTrainerPassword(int id, String password);
    void updateTrainerEmail(int id, String email);
}
