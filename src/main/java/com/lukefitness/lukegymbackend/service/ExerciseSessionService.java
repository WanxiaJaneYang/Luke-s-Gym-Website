package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.ExerciseSession;

import java.util.List;

public interface ExerciseSessionService {
    ExerciseSession insertExerciseSession(ExerciseSession exerciseSession);
    void deleteExerciseSession(Integer id);
    void updateExerciseSession(ExerciseSession exerciseSession);
    List<ExerciseSession> getExerciseSessionByCardId(Integer cardId);

    ExerciseSession getExerciseSessionByCardIdAndExerciseSessionId(Integer cardId, Integer exerciseSessionId);
}
