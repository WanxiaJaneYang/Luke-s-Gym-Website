package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.models.Program;

public interface ProgramService {

    PageInfo<Program> getProgramsForTrainer(Integer trainerId, Integer pageNum, Integer pageSize, String sortBy, String order);
    PageInfo<Program> getProgramsForTrainee(Integer traineeId, Integer pageNum, Integer pageSize, String sortBy, String order);
    void cancelProgram(Integer programId, Integer trainerId);
}
