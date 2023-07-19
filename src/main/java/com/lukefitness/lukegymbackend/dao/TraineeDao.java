package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    void deactivateTrainee(int id);

    int linkTraineeToTrainer(@Param("traineeId") int traineeId, @Param("trainerId") int trainerId);

    List<TraineeResponse> getAllTrainees();
    List<TraineeResponse> getTraineesBySearchUsername(String username);
    List<TraineeResponse> getTraineesBySearchEmail(String email);
    List<TraineeResponse> getTraineesByTrainerId(int trainerId);
    int unlinkTraineeToTrainer(int traineeId);
    List<TraineeResponse> searchLinkedTraineeByUsername(@Param("trainerId") int trainerId, @Param("username") String username);
}
