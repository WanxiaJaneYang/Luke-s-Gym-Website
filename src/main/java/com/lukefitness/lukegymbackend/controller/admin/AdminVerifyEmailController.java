package com.lukefitness.lukegymbackend.controller.admin;

import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin/send-verify-email", "/admin/send-verify-email"})
@Tag(name = "Admin-Account Controller")
public class AdminVerifyEmailController {
    @Autowired
    EmailService emailService;

    @Operation(summary = "Send verify email to trainer by trainer id",
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully sent verify email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainer id not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> sendVerifyEmail() {
        emailService.sendVerifyEmailToTrainer(1);
        return Response.success("Email sent successfully");
    }
}
