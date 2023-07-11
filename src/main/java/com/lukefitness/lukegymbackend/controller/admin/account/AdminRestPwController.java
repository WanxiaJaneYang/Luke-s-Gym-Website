package com.lukefitness.lukegymbackend.controller.admin.account;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.request.Password;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/reset-password")
@RestController
@Tag(name = "Admin-Account Controller")
public class AdminRestPwController {
    @Autowired
    private TrainerService trainerService;

    @Operation(summary = "Reset password for admin account",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "new password", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"password\":\"new-password\"}"))),
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully reset password"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error"
            )
    })
    @PostMapping
    public ResponseEntity<?> resetPassword(@RequestBody Password password){
        if (password == null || password.getPassword() == null || password.getPassword().isEmpty())
            throw new BadRequestException("Password is required");
        trainerService.updateTrainerPassword(1, password.getPassword());
        return Response.success("Password reset successfully");
    }
}
