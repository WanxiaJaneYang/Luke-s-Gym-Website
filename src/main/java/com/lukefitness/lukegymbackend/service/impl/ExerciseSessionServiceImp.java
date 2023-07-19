package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.ExerciseDao;
import com.lukefitness.lukegymbackend.dao.ExerciseSessionDao;
import com.lukefitness.lukegymbackend.dto.request.ExerciseSessionReq;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.Exercise;
import com.lukefitness.lukegymbackend.models.ExerciseExample;
import com.lukefitness.lukegymbackend.models.ExerciseSession;
import com.lukefitness.lukegymbackend.models.ExerciseSessionExample;
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
    public void updateExerciseSession(ExerciseSessionReq exerciseSessionReq, Integer id) {
        ExerciseSession exerciseSession = new ExerciseSession(exerciseSessionReq);
        exerciseSession.setExerciseSessionId(id);
        int affectedRows = exerciseSessionDao.updateByPrimaryKey(exerciseSession);
        if (affectedRows == 0) {
            throw new NotFoundException("ExerciseSession not found");
        }
    }

    @Override
    public List<ExerciseSession> getExerciseSessionByCardId(Integer cardId) {
        ExerciseSessionExample exerciseSessionExample = new ExerciseSessionExample();
        exerciseSessionExample.createCriteria().andCardIdEqualTo(cardId);
        exerciseSessionExample.setOrderByClause("session_type");
        return exerciseSessionDao.selectByExample(exerciseSessionExample);
    }

}
