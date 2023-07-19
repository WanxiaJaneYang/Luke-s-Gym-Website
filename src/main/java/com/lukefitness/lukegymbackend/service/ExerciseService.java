package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.models.Exercise;

import java.util.List;

public interface ExerciseService {
    void addExercise(Exercise exercise);
    void deleteExercise(Integer id);
    List<Exercise> getAllExercises();
    PageInfo<Exercise> getExercisesByPage(Integer pageNo, Integer pageSize);

    PageInfo<Exercise> getExercisesBySearch(String name, Integer pageNo, Integer pageSize);
}
