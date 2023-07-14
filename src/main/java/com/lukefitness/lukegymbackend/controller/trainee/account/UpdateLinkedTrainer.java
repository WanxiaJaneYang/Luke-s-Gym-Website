package com.lukefitness.lukegymbackend.controller.trainee.account;

import com.lukefitness.lukegymbackend.dto.request.TrainerId;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/{traineeId}/link-trainer")
public class UpdateLinkedTrainer {

    @Autowired
    TraineeService traineeService;

    @PatchMapping
    @Operation(summary = "Update the linked trainer of a trainee",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Id of the trainee to update", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Id of the trainer to link to the trainee", required = true)
            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Linked trainer updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid trainer id"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> updateLinkedTrainer(@PathVariable int traineeId, @RequestParam TrainerId trainerId){
        traineeService.linkTraineeToTrainer(traineeId, trainerId.getTrainerId());
        return Response.success();
    }
}
