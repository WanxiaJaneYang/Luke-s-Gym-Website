package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.models.Exercise;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ExerciseService {
    Exercise addExercise(@NotNull String exercise);
    void deleteExercise(Integer id);
    List<Exercise> getAllExercises();
    PageInfo<Exercise> getExercisesByPage(Integer pageNo, Integer pageSize, String orderBy, String orderType);

    PageInfo<Exercise> getExercisesBySearch(String name, Integer pageNo, Integer pageSize, String orderBy, String orderType);
}
