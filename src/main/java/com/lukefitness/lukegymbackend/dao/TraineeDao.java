package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TraineeDao {
    Trainee getTraineeByUsername(String username);
    int traineeRegister(Trainee trainee);
    int traineeLogin(Trainee trainee);
    Trainee getTraineeById(int id);
    Trainee getTraineeByEmail(String email);
    void setEmailVerified(int id);
    void setEmailUnverified(int id);
    void updateTraineePassword(Trainee trainee);
    void updateTraineeEmail(Trainee trainee);
    void deleteNotActiveTrainees();
    void setDeactivationDate(int id);
    void deactivateTrainee();

    List<TraineeResponse> getTraineesByPage(RowBounds rowBounds);
    List<TraineeResponse> getTraineesBySearchUsername(@Param("username") String username, @Param("rowBounds") RowBounds rowBounds);
    List<TraineeResponse> getTraineesBySearchEmail(@Param("email") String email, @Param("rowBounds") RowBounds rowBounds);
}
