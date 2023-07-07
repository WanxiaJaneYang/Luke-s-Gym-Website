package com.lukefitness.lukegymbackend.controller.trainee;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.TraineeContactInfo;
import com.lukefitness.lukegymbackend.service.TraineeContactInfoService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/contact-info")
@Tag(name = "Trainee controller")
public class TraineeContactInfoController {
    @Autowired
    TraineeContactInfoService traineeContactInfoService;

    @GetMapping("{traineeId}")
    public ResponseEntity<?> getTraineeContactInfoByTraineeId(@PathVariable int traineeId) {
        try{
            TraineeContactInfo contactInfo=traineeContactInfoService.getTraineeContactInfoByTraineeId(traineeId);
            return Response.success(contactInfo);
        }catch (BadRequestException e){
            return Response.badRequest(e.getMessage());
        }catch (NotFoundException e){
            return Response.notFound(e.getMessage());
        }catch (Exception e){
            return Response.internalServerError(e.getMessage());
        }
    }

    @PutMapping("{traineeId}")
    public ResponseEntity<?> updateTraineeContact(@PathVariable int traineeId, @RequestBody TraineeContactInfo traineeContactInfo) {
        try{
            TraineeContactInfo contactInfo=traineeContactInfoService.updateTraineeContact(traineeId, traineeContactInfo);
            return Response.success(contactInfo);
        }catch (BadRequestException e){
            return Response.badRequest(e.getMessage());
        }catch (NotFoundException e){
            return Response.notFound(e.getMessage());
        }catch (Exception e){
            return Response.internalServerError(e.getMessage());
        }

    }
}
