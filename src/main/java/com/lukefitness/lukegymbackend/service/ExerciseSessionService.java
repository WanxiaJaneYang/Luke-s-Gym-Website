package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.dto.request.ExerciseSessionReq;
import com.lukefitness.lukegymbackend.models.ExerciseSession;

import java.util.List;

public interface ExerciseSessionService {
    void insertExerciseSession(ExerciseSessionReq exerciseSessionReq);
    void deleteExerciseSession(Integer id);
    void updateExerciseSession(ExerciseSessionReq exerciseSessionReq, Integer id);
    List<ExerciseSession> getExerciseSessionByCardId(Integer cardId);
}
