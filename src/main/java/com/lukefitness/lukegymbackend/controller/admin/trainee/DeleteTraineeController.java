package com.lukefitness.lukegymbackend.controller.admin.trainee;

import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/trainee")
@Tag(name = "Admin-Trainee Controller")
public class DeleteTraineeController {
    @Autowired
    private TraineeService traineeService;

    @Operation(summary = "delete a trainee by id",
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "trainee id", required = true),
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully delete a trainee"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{traineeId}")
    public ResponseEntity<?> deleteTrainee(@PathVariable int traineeId) {
        traineeService.deactivateTrainee(traineeId);
        return Response.success();
    }
}
