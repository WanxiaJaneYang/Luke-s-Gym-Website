package com.lukefitness.lukegymbackend.service;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;

public interface AdminTraineeService {
    PageInfo<TraineeResponse> getTraineesByPage(int page, int size);
    PageInfo<TraineeResponse> getTraineesBySearchUsername(String username, int page, int size);
    PageInfo<TraineeResponse> getTraineesBySearchEmail(String email, int page, int size);
}
