package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Exercise;
import com.lukefitness.lukegymbackend.models.ExerciseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExerciseDao {
    long countByExample(ExerciseExample example);

    int deleteByExample(ExerciseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Exercise row);

    int insertSelective(Exercise row);

    List<Exercise> selectByExample(ExerciseExample example);

    Exercise selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Exercise row, @Param("example") ExerciseExample example);

    int updateByExample(@Param("row") Exercise row, @Param("example") ExerciseExample example);

    int updateByPrimaryKeySelective(Exercise row);

    int updateByPrimaryKey(Exercise row);
}