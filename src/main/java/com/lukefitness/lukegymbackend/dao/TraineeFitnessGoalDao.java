package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.TraineeFitnessGoal;

import java.util.List;

public interface TraineeFitnessGoalDao {
    int deleteByPrimaryKey(Integer traineeId);

    int insert(TraineeFitnessGoal row);

    TraineeFitnessGoal selectByPrimaryKey(Integer traineeId);
    List<TraineeFitnessGoal> selectAll();

    int updateByPrimaryKey(TraineeFitnessGoal row);
}