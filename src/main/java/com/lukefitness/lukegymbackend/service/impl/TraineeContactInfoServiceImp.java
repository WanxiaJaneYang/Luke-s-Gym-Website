package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TraineeContactInfoDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.exception.UserNotExistException;
import com.lukefitness.lukegymbackend.models.TraineeContactInfo;
import com.lukefitness.lukegymbackend.service.TraineeContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraineeContactInfoServiceImp implements TraineeContactInfoService {
    @Autowired
    TraineeContactInfoDao traineeContactInfoDao;

    @Override
    @Transactional
    public void updateTraineeContact(int traineeId,TraineeContactInfo traineeContactInfo) throws BadRequestException {
        traineeContactInfo.setTraineeId(traineeId);
        int afftectedRows= traineeContactInfoDao.updateByPrimaryKey(traineeContactInfo);
        if (afftectedRows == 0) {
            throw new NotFoundException("Trainee contact info not found");
        }
    }

    @Override
    public TraineeContactInfo getTraineeContactInfoByTraineeId(int traineeId) throws Exception {
        TraineeContactInfo traineeContactInfo = traineeContactInfoDao.selectByPrimaryKey(traineeId);
        if (traineeContactInfo == null) {
            throw new UserNotExistException();
        }else {
            return traineeContactInfo;
        }
    }
}
