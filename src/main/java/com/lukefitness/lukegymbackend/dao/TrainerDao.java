package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Trainer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrainerDao {
    Integer registerTrainer(Trainer trainer);
    Trainer getTrainerByName(String username);
    Trainer getTrainerById(int id);
    Trainer getTrainerByEmail(String email);
    void setEmailVerified(int id);
    void setEmailUnverified(int id);

    void updateTrainerPassword(Trainer trainer);
    void updateTrainerEmail(Trainer trainer);
}
