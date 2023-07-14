package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.dto.response.query.SimpleUserQueryResponse;
import com.lukefitness.lukegymbackend.dto.response.register.TrainerResponse;
import com.lukefitness.lukegymbackend.models.Trainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TrainerDao {
    Integer registerTrainer(Trainer trainer);

    void setDeactivationDate(int id);
    void deleteNotActiveTrainers();
    void deactivateTrainer();
    int trainerLogin(Trainer trainer);
    Trainer getTrainerByName(String username);
    Trainer getTrainerById(int id);
    Trainer getTrainerByEmail(String email);
    void setEmailVerified(int id);
    void setEmailUnverified(int id);

    void updateTrainerPassword(Trainer trainer);
    void updateTrainerEmail(Trainer trainer);

    List<SimpleUserQueryResponse> getTrainers(RowBounds rowBounds);
}
