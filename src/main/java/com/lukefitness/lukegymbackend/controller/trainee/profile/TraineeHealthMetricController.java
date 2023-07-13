package com.lukefitness.lukegymbackend.controller.trainee.profile;

import com.lukefitness.lukegymbackend.dao.TraineeHealthMetricDao;
import com.lukefitness.lukegymbackend.models.TraineeHealthMetric;
import com.lukefitness.lukegymbackend.service.TraineeHealthMetricService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/{traineeId}/health-metric")
@Tag(name = "Trainee Profile controller")
public class TraineeHealthMetricController {
    @Autowired
    TraineeHealthMetricService traineeHealthMetricService;
    @Operation(summary = "get trainee health metric by id", security = @SecurityRequirement(name = "bearer-key"),
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee ID", required = true),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Trainee health metric found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee health metric not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @GetMapping
    public ResponseEntity<?> getTraineeHealthMetric(@PathVariable Integer traineeId) throws NotFoundException {
        return Response.success(traineeHealthMetricService.getTraineeHealthMetric(traineeId));
    }

    @Operation(summary = "Update trainee health metric",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee ID", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Trainee health metric object", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeHealthMetric.class))),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Trainee health metric updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Trainee health metric not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee health metric not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping
    public ResponseEntity<?> updateTraineeHealthMetric(@PathVariable Integer traineeId, @RequestBody TraineeHealthMetric traineeHealthMetric) throws NotFoundException {
        traineeHealthMetric.setTraineeId(traineeId);
        traineeHealthMetricService.updateTraineeHealthMetric(traineeHealthMetric);
        return Response.success();
    }
}
