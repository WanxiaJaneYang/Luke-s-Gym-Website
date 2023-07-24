package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.Program;
import com.lukefitness.lukegymbackend.models.ProgramExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramDao {
    long countByExample(ProgramExample example);

    int deleteByExample(ProgramExample example);

    int deleteByPrimaryKey(Integer programId);

    int insert(Program row);

    int insertSelective(Program row);

    List<Program> selectByExample(ProgramExample example);

    Program selectByPrimaryKey(Integer programId);

    int updateByExampleSelective(@Param("row") Program row, @Param("example") ProgramExample example);

    int updateByExample(@Param("row") Program row, @Param("example") ProgramExample example);

    int updateByPrimaryKeySelective(Program row);

    int updateByPrimaryKey(Program row);
}