package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.Trainer;

public interface TrainerService {
    Trainer registerTrainer(Trainer trainer) throws Exception;
    Trainer getTrainerByName(String username) throws BadRequestException;
    Trainer trainerLogin(String username, String password) throws BadRequestException, Exception;
}
