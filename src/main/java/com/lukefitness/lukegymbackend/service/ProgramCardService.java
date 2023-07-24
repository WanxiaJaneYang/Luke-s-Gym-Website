package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.models.ProgramCard;

public interface ProgramCardService {
    ProgramCard insertProgramCard(ProgramCard programCard);
    void deleteProgramCard(Integer cardId, Integer trainerId);
    void updateProgramCard(ProgramCard programCard);
    void sendProgramCard(Integer cardId, Integer trainerId);
    ProgramCard getProgramCard(Integer cardId, Integer trainerId);
    PageInfo<ProgramCard> getProgramCards(Integer trainerId, Integer pageNum, Integer pageSize, String sortBy, String order);
}
