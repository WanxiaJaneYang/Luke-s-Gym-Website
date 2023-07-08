package com.lukefitness.lukegymbackend.controller.trainee.account;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.request.trainee.TraineeEmailReq;
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
@RequestMapping("/trainee/{traineeId}/reset-email")
@Tag(name = "Trainee-Account Controller")
public class TraineeResetEmailAdController {
    @Autowired
    TraineeService traineeService;
    @Operation(summary = "Reset email of trainee by trainee id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "new email", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"email\":\"new-email@example.com\"}"))),
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "trainee id", required = true, example = "6"),
            security = @SecurityRequirement(name = "bearer-key"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully reset email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> resetEmail(@PathVariable int traineeId, @RequestBody TraineeEmailReq traineeEmailReq) {
        if(traineeEmailReq == null || traineeEmailReq.getEmail() == null || traineeEmailReq.getEmail().isEmpty())
            throw new BadRequestException("Email is required");
        traineeService.updateTraineeEmail(traineeId, traineeEmailReq.getEmail());
        return Response.success("Email reset successfully");
    }
}
