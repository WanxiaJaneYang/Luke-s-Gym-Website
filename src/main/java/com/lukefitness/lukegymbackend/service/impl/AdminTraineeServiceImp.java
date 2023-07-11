package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.service.AdminTraineeService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminTraineeServiceImp implements AdminTraineeService {
    @Autowired
    TraineeDao traineeDao;

    @Override
    public List<TraineeResponse> getTraineesByPage(int page, int size) {
        int offset=(page-1)*size;
        RowBounds rowBounds=new RowBounds(offset,size);
        List<TraineeResponse> trainees=traineeDao.getTraineesByPage(rowBounds);
        if (trainees==null||trainees.size()==0){
            throw new NotFoundException("Trainees not found");
        }else{
            return trainees;
        }
    }

    @Override
    public List<TraineeResponse> getTraineesBySearchUsername(String username, int page, int size) {
        int offset=(page-1)*size;
        RowBounds rowBounds=new RowBounds(offset,size);
        List<TraineeResponse> trainees=traineeDao.getTraineesBySearchUsername(username,rowBounds);
        if (trainees==null||trainees.size()==0){
            throw new NotFoundException("Trainees not found");
        }else{
            return trainees;
        }
    }

    @Override
    public List<TraineeResponse> getTraineesBySearchEmail(String email, int page, int size) {
        int offset=(page-1)*size;
        RowBounds rowBounds=new RowBounds(offset,size);
        List<TraineeResponse> trainees=traineeDao.getTraineesBySearchEmail(email,rowBounds);
        if (trainees==null||trainees.size()==0){
            throw new NotFoundException("Trainees not found");
        }else{
            return trainees;
        }
    }

}
