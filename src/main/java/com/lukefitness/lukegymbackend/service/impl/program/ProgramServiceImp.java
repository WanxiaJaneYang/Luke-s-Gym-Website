package com.lukefitness.lukegymbackend.service.impl.program;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dao.ProgramCardDao;
import com.lukefitness.lukegymbackend.dao.ProgramDao;
import com.lukefitness.lukegymbackend.dao.ProgramFeedbackDao;
import com.lukefitness.lukegymbackend.dto.response.ProgramAttendingRes;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.*;
import com.lukefitness.lukegymbackend.service.program.ProgramService;
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

    @Autowired
    ProgramFeedbackDao programFeedbackDao;
    @Override
    public PageInfo<Program> getProgramsForTrainer(ProgramExample programExample, Integer pageNum, Integer pageSize, String sortBy, String order) {
        programExample.setOrderByClause(sortBy + " " + order);
        PageHelper.startPage(pageNum, pageSize);
        List<Program> programs = programDao.selectByExample(programExample);
        return new PageInfo<>(programs);
    }

    @Override
    public PageInfo<Program> getProgramsForTrainee(ProgramExample programExample, Integer pageNum, Integer pageSize, String sortBy, String order) {
        programExample.setOrderByClause(sortBy + " " + order);
        PageHelper.startPage(pageNum, pageSize);
        List<Program> programs = programDao.selectByExample(programExample);
        return new PageInfo<>(programs);
    }

    @Override
    @Transactional
    public Program cancelProgram(Integer programId, Integer trainerId) {
        // check if the program exists and is in upcoming status and belongs to the trainer
        ProgramExample programExample = new ProgramExample();
        programExample.createCriteria().andProgramIdEqualTo(programId)
                .andTrainerIdEqualTo(trainerId)
                .andStatusEqualTo(ProgramStatusEnum.UPCOMING.getValue());
        List<Program> programs = programDao.selectByExample(programExample);
        if(programs.size()==0) {
            throw new NotFoundException("Program not found");
        }
        Program programFromDb = programs.get(0);

        // change the status of the program to cancelled
        programFromDb.setStatus(ProgramStatusEnum.CANCELLED.getValue());
        programDao.updateByPrimaryKeySelective(programFromDb);

        // change the related card status to cancelled
        ProgramCard programCard = programCardDao.selectByPrimaryKey(programFromDb.getCardId());
        programCard.setStatus(ProgramCardStatusEnum.CANCELLED.getValue());
        programCardDao.updateByPrimaryKeySelective(programCard);
        return programFromDb;
    }

    @Override
    @Transactional
    public ProgramAttendingRes attendProgram(Integer trainerId, Integer programId) {
        // check if the program exists and is in upcoming status
        ProgramExample programExample = new ProgramExample();
        programExample.createCriteria().andProgramIdEqualTo(programId).
                andTrainerIdEqualTo(trainerId).
                andStatusEqualTo(ProgramStatusEnum.UPCOMING.getValue());
        List<Program> programs = programDao.selectByExample(programExample);
        if(programs.size()==0) {
            throw new BadRequestException("Program not found or is not in upcoming status or trainer is not the owner of the program");
        }
        Program programFromDb = programs.get(0);
        // change the attendance status of the program to attended
        programFromDb.setAttendance(true);
        // change the status of the program to past
        programFromDb.setStatus(ProgramStatusEnum.PAST.getValue());
        // update the program
        programDao.updateByPrimaryKeySelective(programFromDb);
        // generate the program feedback record
        ProgramFeedback programFeedback =new ProgramFeedback(programId);
        programFeedbackDao.insertSelective(programFeedback);
        return new ProgramAttendingRes(programFeedback, programFromDb);
    }
}
