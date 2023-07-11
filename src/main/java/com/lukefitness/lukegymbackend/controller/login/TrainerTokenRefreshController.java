package com.lukefitness.lukegymbackend.controller.login;

import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.models.response.login.TrainerLoginResponse;
import com.lukefitness.lukegymbackend.service.TrainerService;
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
@RequestMapping("trainer/{trainerId}/token-refresh")
@Tag(name="Refresh Token Controller")
public class TrainerTokenRefreshController {
    @Autowired
    TrainerService trainerService;

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
    public ResponseEntity<?> refreshTraineeToken(@PathVariable int trainerId) {
        Trainer trainer = trainerService.getTrainerById(trainerId);
        TrainerLoginResponse trainerLoginResponse = new TrainerLoginResponse(trainer);
        return Response.success("Token refreshed successfully", trainerLoginResponse);
    }
}
