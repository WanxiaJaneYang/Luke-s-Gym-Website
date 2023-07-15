package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.TraineeHealthMetric;

import java.util.List;

public interface TraineeHealthMetricDao {
    int deleteByPrimaryKey(Integer traineeId);
    int insert(TraineeHealthMetric row);
    TraineeHealthMetric selectByPrimaryKey(Integer traineeId);
    List<TraineeHealthMetric> selectAll();
    int updateByPrimaryKey(TraineeHealthMetric row);
}