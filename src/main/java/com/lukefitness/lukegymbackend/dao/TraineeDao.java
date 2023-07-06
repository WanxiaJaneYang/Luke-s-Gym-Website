package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Trainee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TraineeDao {
    Trainee getTraineeByUsername(String username);
    int traineeRegister(Trainee trainee);
    Trainee getTraineeById(int id);
    Trainee getTraineeByEmail(String email);

}
