package com.lukefitness.lukegymbackend.controller.login;

import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.response.login.TraineeLoginResponse;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trainee/{traineeId}/token-refresh")
@Tag(name="Refresh Token Controller")
public class TraineeTokenRefreshController {
    @Autowired
    TraineeService traineeService;

    @Operation(summary = "refresh token for trainee api",
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully refreshed token"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee id not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping
    public ResponseEntity<?> refreshTraineeToken(@PathVariable int traineeId) {
        Trainee trainee = traineeService.getTraineeById(traineeId);
        TraineeLoginResponse traineeLoginResponse = new TraineeLoginResponse(trainee);
        return Response.success(traineeLoginResponse);
    }
}
