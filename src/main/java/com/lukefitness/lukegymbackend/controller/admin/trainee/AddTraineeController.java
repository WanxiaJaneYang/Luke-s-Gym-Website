package com.lukefitness.lukegymbackend.controller.admin.trainee;

import com.lukefitness.lukegymbackend.models.request.register.UserRegisterReq;
import com.lukefitness.lukegymbackend.models.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/trainee")
public class AddTraineeController {
    @Autowired
    private TraineeService traineeService;

    @PostMapping("/add")
    public ResponseEntity<?> addTrainee(@RequestBody UserRegisterReq traineeRegisterReq) {
        TraineeResponse result=traineeService.traineeRegister(traineeRegisterReq);
        return Response.success(result);
    }
}
