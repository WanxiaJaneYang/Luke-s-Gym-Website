package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.TraineeContactInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TraineeContactInfoDao {
    int insertTraineeContactInfo(int traineeId);
    int updateTraineeContactInfo(TraineeContactInfo traineeContactInfo);
    TraineeContactInfo getTraineeContactInfoByTraineeId(int traineeId);
    void deleteTraineeContactInfo(int traineeId);
}
