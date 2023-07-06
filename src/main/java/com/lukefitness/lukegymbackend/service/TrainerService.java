package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.Trainer;

public interface TrainerService {
    Trainer registerTrainer(Trainer trainer) throws Exception;
    Trainer getTrainerByUsername(String username) throws RuntimeException;
    Trainer trainerLogin(String username, String password) throws BadRequestException, Exception;
    Trainer getTrainerById(int id);
    Trainer getTrainerByEmail(String email);

}
