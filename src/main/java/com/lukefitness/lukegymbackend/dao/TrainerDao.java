package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.dto.response.query.SimpleUserQueryResponse;
import com.lukefitness.lukegymbackend.models.Trainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrainerDao {
    Integer registerTrainer(Trainer trainer);

    int setDeactivationDate(int id);
    void deleteNotActiveTrainers();
    void deactivateTrainer();
    int trainerLogin(Trainer trainer);
    Trainer getTrainerByName(String username);
    Trainer getTrainerById(int id);
    Trainer getTrainerByEmail(String email);
    void setEmailVerified(int id);
    void setEmailUnverified(int id);

    int updateTrainerPassword(Trainer trainer);
    int updateTrainerEmail(Trainer trainer);
    int  updateTrainerUsername(@Param("id") int id, @Param("username") String username);

    List<SimpleUserQueryResponse> getAllTrainers();
    List<SimpleUserQueryResponse> searchTrainerByUsername(@Param("username") String username);
    List<SimpleUserQueryResponse> searchTrainerByEmail(@Param("email") String email);
}
