package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TraineeHealthMetricDao;
import com.lukefitness.lukegymbackend.models.TraineeHealthMetric;
import com.lukefitness.lukegymbackend.service.TraineeHealthMetricService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeHealthMetricServiceImp implements TraineeHealthMetricService{
    @Autowired
    TraineeHealthMetricDao traineeHealthMetricDao;
    @Override
    public TraineeHealthMetric getTraineeHealthMetric(Integer traineeId) throws NotFoundException {
        TraineeHealthMetric result= traineeHealthMetricDao.selectByPrimaryKey(traineeId);
        if (result==null){
           throw new NotFoundException("Trainee health metric not found");
        }else {
            return result;
        }
    }

    @Override
    public void updateTraineeHealthMetric(TraineeHealthMetric traineeHealthMetric) throws NotFoundException {
        int affectedRow=traineeHealthMetricDao.updateByPrimaryKey(traineeHealthMetric);
        if(affectedRow==0){
            throw new NotFoundException("Trainee health metric not found");
        }else if(affectedRow>1){
            throw new NotFoundException("More than one trainee health metric found");
        }
    }
}
