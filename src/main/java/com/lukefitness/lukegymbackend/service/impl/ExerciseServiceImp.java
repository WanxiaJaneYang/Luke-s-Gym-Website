package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.ExerciseDao;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.Exercise;
import com.lukefitness.lukegymbackend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImp implements ExerciseService {
    @Autowired
    ExerciseDao exerciseDao;
    @Override
    public void addExercise(String name) {
        exerciseDao.insert(new Exercise(name));
    }

    @Override
    public void deleteExercise(Integer id) {
        int affectedRows=exerciseDao.deleteByPrimaryKey(id);
        if(affectedRows==0)
            throw new NotFoundException("Exercise not found");
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseDao.selectAll();
    }
}