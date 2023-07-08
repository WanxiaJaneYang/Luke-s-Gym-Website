package com.lukefitness.lukegymbackend.controller.admin.account;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email/admin")
@Tag(name = "Admin-Account Controller")
public class SendRstPwEmailToAdminController {
    @Autowired
    EmailService emailService;

    @Operation(summary = "Send reset password email to admin by username or email",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(in= ParameterIn.QUERY,name = "username", description = "username of the trainer", example = "WanxiaJaneYang"),
                    @io.swagger.v3.oas.annotations.Parameter(in= ParameterIn.QUERY,name = "email", description = "email of the trainer",  example = "wanxiayang86@gmail.com"),
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully sent reset password email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Username or email not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/send-restpw-email")
    public ResponseEntity<?> sendResetEmail(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        if (username != null && !username.isEmpty()) {
            emailService.sendResetPwEmailToTrainerByUsername(username);
        } else if (email != null && !email.isEmpty()) {
            emailService.sendResetPwEmailToTrainerByEmail(email);
        } else {
            throw new BadRequestException("Username or email is required");
        }
        return Response.success("Email sent successfully");
    }
}
