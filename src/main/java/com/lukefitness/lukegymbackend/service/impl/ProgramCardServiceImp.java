package com.lukefitness.lukegymbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dao.ProgramCardDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.models.ProgramCard;
import com.lukefitness.lukegymbackend.models.ProgramCardExample;
import com.lukefitness.lukegymbackend.models.ProgramCardStatusEnum;
import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramCardServiceImp implements ProgramCardService {
    @Autowired
    ProgramCardDao programCardDao;

    @Autowired
    TraineeService traineeService;

    @Override
    public ProgramCard insertProgramCard(ProgramCard programCard) {
        if (programCard.getTraineeId()!=null && !traineeService.isTraineeLinkedToTrainer(programCard.getTraineeId(), programCard.getTrainerId())) {
            throw new UnauthorizedException("Trainee is not linked to trainer");
        }
        programCardDao.insertSelective(programCard);
        return programCardDao.selectByPrimaryKey(programCard.getCardId());
    }

    @Override
    public void deleteProgramCard(Integer cardId, Integer trainerId) {
        ProgramCardExample programCardExample = new ProgramCardExample();
        programCardExample.createCriteria().andCardIdEqualTo(cardId).andTrainerIdEqualTo(trainerId);
        if (programCardDao.deleteByExample(programCardExample)==0) {
            throw new NotFoundException("Program card not found");
        }
    }

    @Override
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
        if(programCardFromDb.getStatus()== ProgramCardStatusEnum.CANCELLED.getValue()) {
            throw new BadRequestException("Program card is cancelled");
        }else if(programCardFromDb.getStatus()==ProgramCardStatusEnum.LOG.getValue()) {
            throw new BadRequestException("Program card is logged");
        }
        programCardDao.updateByPrimaryKeySelective(programCard);
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
