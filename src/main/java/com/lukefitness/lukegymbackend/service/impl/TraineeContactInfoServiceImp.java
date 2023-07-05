package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TraineeContactInfoDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.UserNotExistException;
import com.lukefitness.lukegymbackend.models.TraineeContactInfo;
import com.lukefitness.lukegymbackend.service.TraineeContactInfoService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;

@Service
public class TraineeContactInfoServiceImp implements TraineeContactInfoService {
    @Autowired TraineeContactInfoDao traineeContactInfoDao;

    @Override
    @Transactional
    public TraineeContactInfo updateTraineeContact(int traineeId,TraineeContactInfo traineeContactInfo) throws BadRequestException {
        try{
            traineeContactInfoDao.getTraineeContactInfoByTraineeId(traineeId);
            traineeContactInfo.setTraineeId(traineeId);
            traineeContactInfoDao.updateTraineeContactInfo(traineeContactInfo);
            return traineeContactInfoDao.getTraineeContactInfoByTraineeId(traineeContactInfo.getTraineeId());
        }catch (PersistenceException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public TraineeContactInfo getTraineeContactInfoByTraineeId(int traineeId) throws Exception {
        TraineeContactInfo traineeContactInfo = traineeContactInfoDao.getTraineeContactInfoByTraineeId(traineeId);
        if (traineeContactInfo == null) {
            throw new UserNotExistException();
        }else {
            return traineeContactInfo;
        }
    }
}
