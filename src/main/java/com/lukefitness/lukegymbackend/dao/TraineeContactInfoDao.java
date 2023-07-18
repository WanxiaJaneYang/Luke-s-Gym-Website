package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.TraineeContactInfo;
import java.util.List;

public interface TraineeContactInfoDao {
    int deleteByPrimaryKey(Integer traineeId);

    int insert(TraineeContactInfo row);

    TraineeContactInfo selectByPrimaryKey(Integer traineeId);

    List<TraineeContactInfo> selectAll();

    int updateByPrimaryKey(TraineeContactInfo row);
}