package com.lukefitness.lukegymbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dao.ExerciseDao;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.Exercise;
import com.lukefitness.lukegymbackend.models.ExerciseExample;
import com.lukefitness.lukegymbackend.service.ExerciseService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImp implements ExerciseService {
    @Autowired
    ExerciseDao exerciseDao;
    @Override
    public Exercise addExercise(@NotNull String exercise) {
        exerciseDao.insertSelective(new Exercise(exercise));
        ExerciseExample exerciseExample = new ExerciseExample();
        exerciseExample.createCriteria().andNameEqualTo(exercise);
        return exerciseDao.selectByExample(exerciseExample).get(0);
    }

    @Override
    public void deleteExercise(Integer id) {
        int affectedRows=exerciseDao.deleteByPrimaryKey(id);
        if(affectedRows==0)
            throw new NotFoundException("Exercise not found");
    }

    @Override
    public PageInfo<Exercise> getExercisesBySearch(String name, Integer pageNo, Integer pageSize, String orderByColumn, String orderType) {
        // orderByColumn: name, create_at, update_at
        // orderType: asc, desc
        ExerciseExample exerciseExample = new ExerciseExample();
        exerciseExample.setOrderByClause(orderByColumn + " " + orderType);
        exerciseExample.createCriteria().andNameIgnoreCaseLike("%" + name + "%");
        PageHelper.startPage(pageNo, pageSize);
        List<Exercise> exercises = exerciseDao.selectByExample(exerciseExample);
        PageInfo<Exercise> pageInfo = new PageInfo<>(exercises);
        return pageInfo;
    }

    @Override
    public List<Exercise> getAllExercises() {
        ExerciseExample exerciseExample = new ExerciseExample();
        return exerciseDao.selectByExample(exerciseExample);
    }

    @Override
    public PageInfo<Exercise> getExercisesByPage(Integer pageNo, Integer pageSize, String orderByColumn, String orderType) {
        // orderByColumn: name, create_at, update_at
        // orderType: asc, desc
        ExerciseExample exerciseExample = new ExerciseExample();
        exerciseExample.setOrderByClause(orderByColumn + " " + orderType);

        PageHelper.startPage(pageNo, pageSize);
        List<Exercise> exercises = exerciseDao.selectByExample(exerciseExample);
        PageInfo<Exercise> pageInfo = new PageInfo<>(exercises);
        return pageInfo;
    }
}
