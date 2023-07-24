package com.lukefitness.lukegymbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dao.ProgramCardDao;
import com.lukefitness.lukegymbackend.dao.ProgramDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.models.*;
import com.lukefitness.lukegymbackend.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProgramServiceImp implements ProgramService {
    @Autowired
    ProgramDao programDao;

    @Autowired
    ProgramCardDao programCardDao;
    @Override
    public PageInfo<Program> getProgramsForTrainer(ProgramExample programExample, Integer pageNum, Integer pageSize, String sortBy, String order) {
        PageHelper.startPage(pageNum, pageSize, sortBy + " " + order);
        List<Program> programs = programDao.selectByExample(programExample);
        return new PageInfo<>(programs);
    }

    @Override
    public PageInfo<Program> getProgramsForTrainee(Integer traineeId, Integer pageNum, Integer pageSize, String sortBy, String order) {
        ProgramExample programExample = new ProgramExample();
        programExample.createCriteria().andTraineeIdEqualTo(traineeId);
        PageHelper.startPage(pageNum, pageSize, sortBy + " " + order);
        List<Program> programs = programDao.selectByExample(programExample);
        return new PageInfo<>(programs);
    }

    @Override
    @Transactional
    public void cancelProgram(Integer programId, Integer trainerId) {
        Program programFromDb = programDao.selectByPrimaryKey(programId);
        if(programFromDb==null) {
            throw new NotFoundException("Program not found");
        }
        // check if the trainer is the owner of the program
        if(!programFromDb.getTrainerId().equals(trainerId)) {
            throw new UnauthorizedException("Trainer is not the owner of the program");
        }
        // check if the program is in draft status
        if(!programFromDb.getStatus().equals(ProgramStatusEnum.UPCOMING.getValue())) {
            throw new BadRequestException("Program is not in upcoming status");
        }

        // change the status of the program to cancelled
        programFromDb.setStatus(ProgramStatusEnum.CANCELLED.getValue());
        programDao.updateByPrimaryKeySelective(programFromDb);

        // change the related card status to cancelled
        ProgramCard programCard = programCardDao.selectByPrimaryKey(programFromDb.getCardId());
        programCard.setStatus(ProgramCardStatusEnum.CANCELLED.getValue());
        programCardDao.updateByPrimaryKeySelective(programCard);
    }
}
