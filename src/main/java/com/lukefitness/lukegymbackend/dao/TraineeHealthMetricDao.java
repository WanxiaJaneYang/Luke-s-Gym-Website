package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.TraineeHealthMetric;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TraineeHealthMetricDao {
    int deleteByPrimaryKey(Integer traineeId);
    int insert(TraineeHealthMetric row);
    TraineeHealthMetric selectByPrimaryKey(Integer traineeId);
    List<TraineeHealthMetric> selectAll();
    int updateByPrimaryKey(TraineeHealthMetric row);
}