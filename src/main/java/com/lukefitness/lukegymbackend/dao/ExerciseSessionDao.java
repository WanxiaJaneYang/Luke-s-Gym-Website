package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.ExerciseSession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseSessionDao {
    int deleteByPrimaryKey(Integer exerciseSessionId);

    int insert(ExerciseSession row);

    ExerciseSession selectByPrimaryKey(Integer exerciseSessionId);

    List<ExerciseSession> selectAll();

    int updateByPrimaryKey(ExerciseSession row);
    List<ExerciseSession> selectByCardId(Integer cardId);
}