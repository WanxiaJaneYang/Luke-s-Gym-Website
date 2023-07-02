package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Trainer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrainerDao {
    Integer registerTrainer(Trainer trainer);
    Trainer getTrainerByName(String username);
    Trainer trainerLogin(String username, String password);
}
