package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.ExerciseDao;
import com.lukefitness.lukegymbackend.dao.ExerciseSessionDao;
import com.lukefitness.lukegymbackend.dao.ProgramCardDao;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.*;
import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExerciseSessionServiceImp implements ExerciseSessionService {
    @Autowired
    ExerciseSessionDao exerciseSessionDao;

    @Autowired
    ExerciseDao exerciseDao;

    @Autowired
    ProgramCardDao programCardDao;
    @Override
    @Transactional
    public ExerciseSession insertExerciseSession(ExerciseSession exerciseSession) {
        ExerciseExample exerciseExample = new ExerciseExample();
        exerciseExample.createCriteria().andNameEqualTo(exerciseSession.getName());
        if(exerciseDao.selectByExample(exerciseExample).isEmpty())
            exerciseDao.insert(new Exercise(exerciseSession.getName()));
        exerciseSessionDao.insertSelective(exerciseSession);
        return exerciseSession;
    }

    @Override
    public void deleteExerciseSession(Integer id) {
        int affectedRows = exerciseSessionDao.deleteByPrimaryKey(id);
        if (affectedRows == 0) {
            throw new NotFoundException("ExerciseSession not found");
        }
    }

    @Override
    @Transactional
    public void updateExerciseSession(ExerciseSession exerciseSession) {
        if (exerciseSession.getName()!=null) {
            ExerciseExample exerciseExample = new ExerciseExample();
            exerciseExample.createCriteria().andNameEqualTo(exerciseSession.getName());
            if(exerciseDao.selectByExample(exerciseExample).isEmpty())
                exerciseDao.insert(new Exercise(exerciseSession.getName()));
        }
        int rows=exerciseSessionDao.updateByPrimaryKeySelective(exerciseSession);
        if(rows==0)
            throw new NotFoundException("ExerciseSession not found");

        //update card
        ExerciseSession exerciseSessionFromDb=exerciseSessionDao.selectByPrimaryKey(exerciseSession.getExerciseSessionId());
        ProgramCard programCard = new ProgramCard(exerciseSessionFromDb.getCardId());
        programCard.setUpdateAt(exerciseSessionFromDb.getUpdateAt());
        programCardDao.updateByPrimaryKeySelective(programCard);
    }

    @Override
    public List<ExerciseSession> getExerciseSessionByCardId(Integer cardId) {
        ExerciseSessionExample exerciseSessionExample = new ExerciseSessionExample();
        exerciseSessionExample.createCriteria().andCardIdEqualTo(cardId);
        exerciseSessionExample.setOrderByClause("session_type");
        return exerciseSessionDao.selectByExample(exerciseSessionExample);
    }

    @Override
    public ExerciseSession getExerciseSessionByCardIdAndExerciseSessionId( Integer cardId, Integer exerciseSessionId) {
        ExerciseSessionExample exerciseSessionExample = new ExerciseSessionExample();
        exerciseSessionExample.createCriteria().andCardIdEqualTo(cardId).andExerciseSessionIdEqualTo(exerciseSessionId);
        List<ExerciseSession> exerciseSessions = exerciseSessionDao.selectByExample(exerciseSessionExample);
        if (exerciseSessions.isEmpty()) {
            throw new NotFoundException("ExerciseSession not found");
        }
        return exerciseSessions.get(0);
    }

}
