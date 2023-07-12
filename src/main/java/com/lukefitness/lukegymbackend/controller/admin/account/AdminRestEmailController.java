package com.lukefitness.lukegymbackend.controller.admin.account;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.request.Email;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/reset-email")
@Tag(name = "Admin-Account Controller")
public class AdminRestEmailController {
    @Autowired
    private TrainerService trainerService;
    @PostMapping
    @Operation(summary = "Reset email for admin account",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "new email", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"email\":\"new-email@example.com\"}"))
            ),
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully reset email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error"
            )
    })
    public ResponseEntity<?> resetEmail(@RequestBody Email email){
        if (email == null || email.getEmail() == null || email.getEmail().isEmpty())
            throw new BadRequestException("Email is required");
        trainerService.updateTrainerEmail(1, email.getEmail());
        return Response.success();
    }
}
