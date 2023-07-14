package com.lukefitness.lukegymbackend.controller.trainee.account;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.dto.request.Password;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/{traineeId}/reset-password")
@Tag(name = "Trainee-Account Controller")
public class TraineeResetPwController {
    @Autowired
    TraineeService traineeService;

    @Autowired
    EmailService emailService;

    @Operation(summary = "Reset password of trainee by trainee id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "new password", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"password\":\"new-password\"}"))),
            security = @SecurityRequirement(name="bearer-key"),
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "trainee id", required = true, example = "6"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully reset password"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> resetPassword(@PathVariable int traineeId, @RequestBody Password password) {
        if (password == null || password.getPassword() == null || password.getPassword().isEmpty())
            throw new BadRequestException("Password is required");
        Trainee trainee = traineeService.getTraineeById(traineeId);
        traineeService.updateTraineePassword(traineeId, password.getPassword());
        emailService.sendResetSuccessEmail(trainee);
        return Response.success();
    }
}
