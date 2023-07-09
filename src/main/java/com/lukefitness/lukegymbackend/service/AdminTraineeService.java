package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.response.register.TraineeResponse;

import java.util.List;

public interface AdminTraineeService {
    List<TraineeResponse> getTraineesByPage(int page, int size);
    List<TraineeResponse> getTraineesBySearchUsername(String username, int page, int size);
    List<TraineeResponse> getTraineesBySearchEmail(String email, int page, int size);
}
