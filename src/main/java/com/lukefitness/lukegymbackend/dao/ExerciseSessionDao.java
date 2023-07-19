package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.ExerciseSession;
import com.lukefitness.lukegymbackend.models.ExerciseSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExerciseSessionDao {
    long countByExample(ExerciseSessionExample example);

    int deleteByExample(ExerciseSessionExample example);

    int deleteByPrimaryKey(Integer exerciseSessionId);

    int insert(ExerciseSession row);

    int insertSelective(ExerciseSession row);

    List<ExerciseSession> selectByExample(ExerciseSessionExample example);

    ExerciseSession selectByPrimaryKey(Integer exerciseSessionId);

    int updateByExampleSelective(@Param("row") ExerciseSession row, @Param("example") ExerciseSessionExample example);

    int updateByExample(@Param("row") ExerciseSession row, @Param("example") ExerciseSessionExample example);

    int updateByPrimaryKeySelective(ExerciseSession row);

    int updateByPrimaryKey(ExerciseSession row);
}