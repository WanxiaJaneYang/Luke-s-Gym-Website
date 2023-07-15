package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Exercise;
import java.util.List;

public interface ExerciseDao {
    int deleteByPrimaryKey(Integer id);
    void insert(Exercise row);
    Exercise selectByPrimaryKey(Integer id);
    List<Exercise> selectAll();
    int updateByPrimaryKey(Exercise row);
}