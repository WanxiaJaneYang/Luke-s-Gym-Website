package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Exercise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ExerciseDao {
    int deleteByPrimaryKey(Integer id);
    void insert(Exercise row);
    Exercise selectByPrimaryKey(Integer id);

    Exercise selectByName(String name);
    List<Exercise> selectAll();
    int updateByPrimaryKey(Exercise row);
}