package com.lukefitness.lukegymbackend.controller;

import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    TrainerService trainerService;

    @PostMapping("/register")
    public ResponseEntity trainerRegister(@RequestBody Trainer trainer){
        try {
            Trainer trainerTemp = trainerService.registerTrainer(trainer);
            return Response.successCreated(trainerTemp);
        }catch (RuntimeException e){
            return Response.error(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
