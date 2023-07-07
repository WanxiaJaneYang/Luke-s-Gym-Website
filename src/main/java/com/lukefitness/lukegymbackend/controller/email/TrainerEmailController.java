package com.lukefitness.lukegymbackend.controller.email;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
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
@RequestMapping("/email/trainer")
@Tag(name = "Email controller")
public class TrainerEmailController {
    @Autowired
    EmailService emailService;

    @Operation(summary = "Send verify email to trainer by trainer id",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(in=ParameterIn.PATH,name = "trainerId", description = "id of the trainer", required = true, example = "19"),
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully sent verify email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainer id not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{trainerId}/send-verify-email")
    public ResponseEntity<?> sendVerifyEmail(@PathVariable int trainerId) {
        try {
            emailService.sendVerifyEmailToTrainer(trainerId);
            return Response.success("Email sent successfully");
        } catch (BadRequestException e) {
            return Response.badRequest(e.getMessage());
        } catch(NotFoundException e){
            return Response.notFound(e.getMessage());
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage());
        }
    }

    @Operation(summary = "Send reset password email to trainer by username or email",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(in= ParameterIn.QUERY,name = "username", description = "username of the trainer", required = false, example = "WanxiaJaneYang"),
                    @io.swagger.v3.oas.annotations.Parameter(in= ParameterIn.QUERY,name = "email", description = "email of the trainer", required = false, example = "wanxiayang86@gmail.com"),
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully sent reset password email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Username or email not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/reset-password")
    public ResponseEntity<?> sendResetEmail(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        try {
            if (username != null && !username.isEmpty()) {
                emailService.sendResetPwEmailToTrainerByUsername(username);
            } else if (email != null && !email.isEmpty()) {
                emailService.sendResetPwEmailToTrainerByEmail(email);
            } else {
                throw new BadRequestException("Username or email is required");
            }
            return Response.success("Email sent successfully");
        } catch (BadRequestException e) {
            return Response.badRequest(e.getMessage());
        } catch(NotFoundException e){
            return Response.notFound(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(e.getMessage());
        }
    }
}
