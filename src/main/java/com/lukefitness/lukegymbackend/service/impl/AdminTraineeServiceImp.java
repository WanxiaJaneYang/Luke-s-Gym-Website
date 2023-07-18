package com.lukefitness.lukegymbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.service.AdminTraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminTraineeServiceImp implements AdminTraineeService {
    @Autowired
    TraineeDao traineeDao;

    @Override
    public PageInfo<TraineeResponse> getTraineesByPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<TraineeResponse> trainees=traineeDao.getAllTrainees();
        return new PageInfo<>(trainees);
    }

    @Override
    public PageInfo<TraineeResponse> getTraineesBySearchUsername(String username, int page, int size) {
        PageHelper.startPage(page,size);
        List<TraineeResponse> trainees=traineeDao.getTraineesBySearchUsername(username);
        return new PageInfo<>(trainees);
    }

    @Override
    public PageInfo<TraineeResponse> getTraineesBySearchEmail(String email, int page, int size) {
        PageHelper.startPage(page,size);
        List<TraineeResponse> trainees=traineeDao.getTraineesBySearchEmail(email);
        return new PageInfo<>(trainees);
    }

}
