package com.lukefitness.lukegymbackend.controller.send_verify_email;

import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send-verify-email/trainer/{trainerId}")
@Tag(name = "Send Verify Email Controller")
public class TrainerVerifyEmailController {
    @Autowired
    EmailService emailService;

    @Operation(summary = "Send verify email to trainer by trainer id",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(in= ParameterIn.PATH,name = "trainerId", description = "id of the trainer", required = true, example = "19"),
            },
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully sent verify email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainer id not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> sendVerifyEmail(@PathVariable int trainerId) {
            emailService.sendVerifyEmailToTrainer(trainerId);
            return Response.success();
    }
}
