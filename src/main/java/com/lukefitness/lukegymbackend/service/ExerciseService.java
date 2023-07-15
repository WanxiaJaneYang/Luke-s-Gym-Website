package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.models.Exercise;

import java.util.List;

public interface ExerciseService {
    void addExercise(String name);
    void deleteExercise(Integer id);
    List<Exercise> getAllExercises();
    PageInfo<Exercise> getExercisesByPage(Integer pageNo, Integer pageSize);
}
