package com.lukefitness.lukegymbackend.controller.trainer.trainee;

import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.TraineeContactInfo;
import com.lukefitness.lukegymbackend.models.TraineeFitnessGoal;
import com.lukefitness.lukegymbackend.models.TraineeHealthMetric;
import com.lukefitness.lukegymbackend.service.TraineeContactInfoService;
import com.lukefitness.lukegymbackend.service.TraineeFitnessGoalService;
import com.lukefitness.lukegymbackend.service.TraineeHealthMetricService;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer/{trainerId}/trainee/{traineeId}")
@Tag(name = "Trainer-Trainee Controller")
public class TrainerGetTraineeProfileController {
    @Autowired
    TraineeService traineeService;

    @Autowired
    TraineeContactInfoService traineeContactInfoService;

    @Autowired
    TraineeHealthMetricService traineeHealthMetricService;

    @Autowired
    TraineeFitnessGoalService traineeFitnessGoalService;

    @GetMapping("/contact-info")
    @Operation(summary = "Get trainee's contact info",
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Id of the trainer", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Id of the trainee", required = true)
            },
            responses = {
            @ApiResponse(responseCode = "200", description = "Trainee's contact info retrieved successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeContactInfo.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Trainee not found"),
            }
    )
    public ResponseEntity<?> getTraineeContactInfo(@PathVariable int trainerId, @PathVariable int traineeId) throws Exception {
        Trainee trainee = traineeService.getTraineeById(traineeId);
        if(trainee.getTrainer_id()!=trainerId)
            return Response.unauthorized("You are not authorized to view this trainee's contact info as you are not their trainer");
        return Response.success(traineeContactInfoService.getTraineeContactInfoByTraineeId(traineeId));
    }

    @GetMapping("/health-metrics")
    @Operation(summary = "Get trainee's health metrics",
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Id of the trainer", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Id of the trainee", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Trainee's health metrics retrieved successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeHealthMetric.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Trainee not found"),
            }
    )
    public ResponseEntity<?> getTraineeHealthMetric(@PathVariable int traineeId, @PathVariable int trainerId) throws Exception {
        Trainee trainee = traineeService.getTraineeById(traineeId);
        if(trainee.getTrainer_id()!=trainerId)
            return Response.unauthorized("You are not authorized to view this trainee's health metrics as you are not their trainer");
        return Response.success(traineeHealthMetricService.getTraineeHealthMetric(traineeId));
    }

    @GetMapping("/fitness-goals")
    @Operation(summary = "Get trainee's fitness goals",
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Id of the trainer", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Id of the trainee", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Trainee's fitness goals retrieved successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeFitnessGoal.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Trainee not found"),
            }
    )
    public ResponseEntity<?> getTraineeFitnessGoals(@PathVariable int traineeId, @PathVariable int trainerId) throws NotFoundException {
        Trainee trainee = traineeService.getTraineeById(traineeId);
        if(trainee.getTrainer_id()!=trainerId)
            return Response.unauthorized("You are not authorized to view this trainee's fitness goals as you are not their trainer");
        return Response.success(traineeFitnessGoalService.getTraineeFitnessGoal(traineeId));
    }
}
