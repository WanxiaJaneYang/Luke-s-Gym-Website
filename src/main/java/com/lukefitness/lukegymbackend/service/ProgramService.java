package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.models.Program;
import com.lukefitness.lukegymbackend.models.ProgramExample;

public interface ProgramService {

    PageInfo<Program> getProgramsForTrainer(ProgramExample programExample, Integer pageNum, Integer pageSize, String sortBy, String order);
    PageInfo<Program> getProgramsForTrainee(ProgramExample programExample, Integer pageNum, Integer pageSize, String sortBy, String order);
    void cancelProgram(Integer programId, Integer trainerId);
}
