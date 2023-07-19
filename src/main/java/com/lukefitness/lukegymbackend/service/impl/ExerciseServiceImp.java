package com.lukefitness.lukegymbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public void addExercise(Exercise exercise) {
        exerciseDao.insert(exercise);
    }

    @Override
    public void deleteExercise(Integer id) {
        int affectedRows=exerciseDao.deleteByPrimaryKey(id);
        if(affectedRows==0)
            throw new NotFoundException("Exercise not found");
    }

    @Override
    public PageInfo<Exercise> getExercisesBySearch(String name, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Exercise> exercises = exerciseDao.searchByName(name);
        PageInfo<Exercise> pageInfo = new PageInfo<>(exercises);
        return pageInfo;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseDao.selectAll();
    }

    @Override
    public PageInfo<Exercise> getExercisesByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Exercise> exercises = exerciseDao.selectAll();
        PageInfo<Exercise> pageInfo = new PageInfo<>(exercises);
        return pageInfo;
    }
}
