package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.ProgramCard;
import com.lukefitness.lukegymbackend.models.ProgramCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramCardDao {
    long countByExample(ProgramCardExample example);

    int deleteByExample(ProgramCardExample example);

    int deleteByPrimaryKey(Integer cardId);

    int insert(ProgramCard row);

    int insertSelective(ProgramCard row);

    List<ProgramCard> selectByExample(ProgramCardExample example);

    ProgramCard selectByPrimaryKey(Integer cardId);

    int updateByExampleSelective(@Param("row") ProgramCard row, @Param("example") ProgramCardExample example);

    int updateByExample(@Param("row") ProgramCard row, @Param("example") ProgramCardExample example);

    int updateByPrimaryKeySelective(ProgramCard row);

    int updateByPrimaryKey(ProgramCard row);
}