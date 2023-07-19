package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.dto.request.ExerciseSessionReq;
import com.lukefitness.lukegymbackend.models.ExerciseSession;

import java.util.List;

public interface ExerciseSessionService {
    ExerciseSession insertExerciseSession(ExerciseSession exerciseSession);
    void deleteExerciseSession(Integer id);
    void updateExerciseSession(ExerciseSessionReq exerciseSessionReq, Integer id);
    List<ExerciseSession> getExerciseSessionByCardId(Integer cardId);
}