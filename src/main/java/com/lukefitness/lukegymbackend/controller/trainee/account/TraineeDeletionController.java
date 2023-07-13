package com.lukefitness.lukegymbackend.controller.trainee.account;

import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trainee/{traineeId}/")
@Tag(name = "Trainee-Account Controller")
public class TraineeDeletionController {
    @Autowired
    TraineeService traineeService;

    @Autowired
    EmailService emailService;
    @DeleteMapping
    @Operation(summary = "Delete trainee by trainee id",
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully deleted trainee"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> deleteTrainee(@PathVariable int traineeId) throws NotFoundException {
        Trainee trainee=traineeService.getTraineeById(traineeId);
        if (trainee == null)
            throw new NotFoundException("Trainee not found");

        traineeService.deactivateTrainee(traineeId);
        emailService.sendTraineeDeletionEmail(trainee);
        return Response.success();
    }
}
