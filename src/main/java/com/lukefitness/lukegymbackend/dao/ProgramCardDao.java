package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.ProgramCard;

import java.util.List;

public interface ProgramCardDao {
    int deleteByPrimaryKey(Integer cardId);

    int insert(ProgramCard row);

    ProgramCard selectByPrimaryKey(Integer cardId);

    List<ProgramCard> selectAll();

    int updateByPrimaryKey(ProgramCard row);
}