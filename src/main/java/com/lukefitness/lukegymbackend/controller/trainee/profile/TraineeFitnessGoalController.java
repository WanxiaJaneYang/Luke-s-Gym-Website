package com.lukefitness.lukegymbackend.controller.trainee.profile;

import com.lukefitness.lukegymbackend.models.TraineeFitnessGoal;
import com.lukefitness.lukegymbackend.service.TraineeFitnessGoalService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/{traineeId}/fitness-goal")
@Tag(name = "Trainee Profile controller")
public class TraineeFitnessGoalController {
    @Autowired
    TraineeFitnessGoalService traineeFitnessGoalService;

    @GetMapping
    @Operation(summary = "get trainee fitness goal",
    security = @SecurityRequirement(name = "bearer-key"),
    parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee id", required = true),
    responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Trainee fitness goal found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee fitness goal not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<?> getTraineeFitnessGoal(@PathVariable int traineeId) throws NotFoundException {
        return Response.success(traineeFitnessGoalService.getTraineeFitnessGoal(traineeId));
    }

    @Operation(summary = "Update trainee fitness goal",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee id", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeFitnessGoal.class)
            )),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Trainee fitness goal updated"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee fitness goal not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            })
    @PutMapping
    public ResponseEntity<?> updateTraineeFitnessGoal(@PathVariable int traineeId, @RequestBody TraineeFitnessGoal traineeFitnessGoal) throws NotFoundException {
        traineeFitnessGoal.setTraineeId(traineeId);
        traineeFitnessGoalService.updateTraineeFitnessGoal(traineeFitnessGoal);
        return Response.success();
    }
}
