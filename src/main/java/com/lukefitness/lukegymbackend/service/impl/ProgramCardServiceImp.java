package com.lukefitness.lukegymbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dao.ProgramCardDao;
import com.lukefitness.lukegymbackend.dao.ProgramDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.models.*;
import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProgramCardServiceImp implements ProgramCardService {
    @Autowired
    ProgramCardDao programCardDao;

    @Autowired
    TraineeService traineeService;

    @Autowired
    ProgramDao programDao;

    @Override
    public ProgramCard insertProgramCard(ProgramCard programCard) {
        if (programCard.getTraineeId()!=null && !traineeService.isTraineeLinkedToTrainer(programCard.getTraineeId(), programCard.getTrainerId())) {
            throw new UnauthorizedException("Trainee is not linked to trainer");
        }
        programCardDao.insertSelective(programCard);
        return programCardDao.selectByPrimaryKey(programCard.getCardId());
    }

    @Override
    @Transactional
    public void deleteProgramCard(Integer cardId, Integer trainerId) {
        ProgramCardExample programCardExample = new ProgramCardExample();
        programCardExample.createCriteria().andCardIdEqualTo(cardId).andTrainerIdEqualTo(trainerId);
        ProgramCard programCardFromDb = programCardDao.selectByPrimaryKey(cardId);
        if(!programCardFromDb.getStatus().equals(ProgramCardStatusEnum.DRAFT.getValue()))
            throw new BadRequestException("Program card is not in draft status");
        if (programCardDao.deleteByExample(programCardExample)==0) {
            throw new NotFoundException("Program card not found");
        }
    }

    @Override
    @Transactional
    public void updateProgramCard(ProgramCard programCard) {
        ProgramCard programCardFromDb = programCardDao.selectByPrimaryKey(programCard.getCardId());
        if(programCardFromDb==null) {
            throw new NotFoundException("Program card not found");
        }
        // check if the trainer is the owner of the program card
        if(!programCardFromDb.getTrainerId().equals(programCard.getTrainerId())) {
            throw new UnauthorizedException("Trainer is not the owner of the program card");
        }
        //check if the trainee is linked to the trainer
        if(programCard.getTraineeId()!=null && !traineeService.isTraineeLinkedToTrainer(programCard.getTraineeId(), programCard.getTrainerId())) {
            throw new UnauthorizedException("Trainee is not linked to trainer");
        }
        // check if the card is not cancelled or logged
        if(programCardFromDb.getStatus().equals( ProgramCardStatusEnum.CANCELLED.getValue())) {
            throw new BadRequestException("Program card is cancelled");
        }else if(programCardFromDb.getStatus().equals(ProgramCardStatusEnum.LOG.getValue())) {
            throw new BadRequestException("Program card is logged");
        }
        programCardDao.updateByPrimaryKeySelective(programCard);

        //if the program card is sent, change the program as well
        if(programCardFromDb.getStatus().equals(ProgramCardStatusEnum.SENT.getValue())) {
            //get the program by card id
            ProgramExample programExample = new ProgramExample();
            programExample.createCriteria().andCardIdEqualTo(programCard.getCardId());
            Program programFromDb=programDao.selectByExample(programExample).get(0);

            //update the program
            Program program = new Program(programCard);
            program.setProgramId(programFromDb.getProgramId());
            programDao.updateByPrimaryKeySelective(program);
        }

    }

    @Override
    @Transactional
    public void sendProgramCard(Integer cardId, Integer trainerId) {
        ProgramCard programCardFromDb = programCardDao.selectByPrimaryKey(cardId);
        if(programCardFromDb==null) {
            throw new NotFoundException("Program card not found");
        }
        // check if the trainer is the owner of the program card
        if(!programCardFromDb.getTrainerId().equals(trainerId)) {
            throw new UnauthorizedException("Trainer is not the owner of the program card");
        }
        // check if the card is not cancelled or logged
       if(!programCardFromDb.getStatus().equals(ProgramCardStatusEnum.DRAFT.getValue())) {
            System.out.println(programCardFromDb.getStatus());
            System.out.println(ProgramCardStatusEnum.DRAFT.getValue());
            throw new BadRequestException("Only draft program cards can be sent");
        }
        programCardFromDb.setStatus(ProgramCardStatusEnum.SENT.getValue());
        programCardDao.updateByPrimaryKeySelective(programCardFromDb);

        //generate a new program for the trainee
        if(programCardFromDb.getTraineeId()==null) {
            throw new BadRequestException("Missing trainee id");
        }
        if(programCardFromDb.getDate()==null||programCardFromDb.getDuration()==null) {
            throw new BadRequestException("Missing date or duration");
        }
        programDao.insertSelective(new Program(programCardFromDb));
    }

    @Override
    public ProgramCard getProgramCard(Integer cardId, Integer trainerId) {

        ProgramCard programCard = programCardDao.selectByPrimaryKey(cardId);
        if(programCard==null) {
            throw new NotFoundException("Program card not found");
        }
        // check if the trainer is the owner of the program card
        if(!programCard.getTrainerId().equals(trainerId)) {
            throw new UnauthorizedException("Trainer is not the owner of the program card");
        }
        return programCardDao.selectByPrimaryKey(cardId);
    }

    @Override
    public PageInfo<ProgramCard> getProgramCards(Integer trainerId, Integer pageNum, Integer pageSize, String sortBy, String order) {
        ProgramCardExample programCardExample = new ProgramCardExample();
        programCardExample.createCriteria().andTrainerIdEqualTo(trainerId);
        programCardExample.setOrderByClause(sortBy + " " + order);
        PageHelper.startPage(pageNum, pageSize);
        List<ProgramCard> programCards = programCardDao.selectByExample(programCardExample);
        return new PageInfo<>(programCards);
    }
}
