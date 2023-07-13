package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TraineeFitnessGoalDao;
import com.lukefitness.lukegymbackend.models.TraineeFitnessGoal;
import com.lukefitness.lukegymbackend.service.TraineeFitnessGoalService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class TraineeFitnessGoalServiceImp implements TraineeFitnessGoalService {
    @Autowired
    TraineeFitnessGoalDao traineeFitnessGoalDao;
    @GetMapping
    @Override
    public TraineeFitnessGoal getTraineeFitnessGoal(int traineeId) throws NotFoundException {
        TraineeFitnessGoal result=traineeFitnessGoalDao.selectByPrimaryKey(traineeId);
        if (result==null){
            throw new NotFoundException("Trainee fitness goal not found");
        }else {
            return result;
        }
    }

    @PutMapping
    @Override
    public void updateTraineeFitnessGoal(TraineeFitnessGoal traineeFitnessGoal) throws NotFoundException {
        int rowAffected=traineeFitnessGoalDao.updateByPrimaryKey(traineeFitnessGoal);
        if(rowAffected==0){
            throw new NotFoundException("Trainee fitness goal not found");
        }
    }
}
