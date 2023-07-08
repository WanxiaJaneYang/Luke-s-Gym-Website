package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.Trainer;

import java.util.Map;

public interface TrainerService {
    Trainer registerTrainer(Trainer trainer) throws Exception;
    Trainer getTrainerByUsername(String username) throws RuntimeException;
    Map trainerLogin(String username, String password) throws BadRequestException, Exception;
    Trainer getTrainerById(int id);
    Trainer getTrainerByEmail(String email);

    void updateTrainerPassword(int id, String password);
    void updateTrainerEmail(int id, String email);
}
