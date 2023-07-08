package com.lukefitness.lukegymbackend.controller.trainer;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.request.Email;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/reset-email")
@Tag(name = "Trainer-Account Controller")
public class TrainerRestEmailAdController {
    @Autowired
    TrainerService trainerService;

    @Operation(summary = "Reset email of trainer by trainer id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "new email", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"email\":\"new-email@example.com\"}"))),
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "trainer id", required = true, example = "20"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully reset email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> resetEmail(@PathVariable int trainerId, @RequestBody Email email) {
        if(email == null || email.getEmail() == null || email.getEmail().isEmpty())
            throw new BadRequestException("Email is required");
        trainerService.updateTrainerEmail(trainerId, email.getEmail());
        return Response.success("Email reset successfully");
    }

}
