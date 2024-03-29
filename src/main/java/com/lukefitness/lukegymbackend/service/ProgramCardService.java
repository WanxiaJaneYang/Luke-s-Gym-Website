package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dto.response.SendProgramCardRes;
import com.lukefitness.lukegymbackend.models.ProgramCard;
import com.lukefitness.lukegymbackend.models.ProgramCardExample;

public interface ProgramCardService {
    ProgramCard insertProgramCard(ProgramCard programCard);
    void deleteProgramCard(Integer cardId, Integer trainerId);
    ProgramCard updateProgramCard(ProgramCard programCard);
    SendProgramCardRes sendProgramCard(Integer cardId, Integer trainerId);
    ProgramCard getProgramCard(Integer cardId, Integer trainerId);
    PageInfo<ProgramCard> getProgramCardsByExample(ProgramCardExample programCardExample, Integer pageNum, Integer pageSize, String sortBy, String order);

    ProgramCard getProgramCardByExample(ProgramCardExample example);
}
