package com.lukefitness.lukegymbackend.controller.email;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email/trainee")
@Tag(name = "Email controller")
public class TraineeEmailController {
    @Autowired
    EmailService emailService;

    @Operation(summary = "Send verify email to trainee by trainee id",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(in =ParameterIn.PATH ,name = "traineeId", description = "id of the trainee", required = true, example = "6"),
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully sent verify email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee id not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{traineeId}/verify")
    public ResponseEntity<?> sendVerifyEmail(@PathVariable int traineeId) {
        try {
            emailService.sendVerifyEmailToTrainee(traineeId);
            return Response.success("Email sent successfully");
        } catch (BadRequestException e) {
            return Response.badRequest(e.getMessage());
        }catch (NotFoundException e){
            return Response.notFound(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(e.getMessage());
        }
    }


    @Operation(summary = "send reset email to trainee",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY,name = "username",description = "username of the trainee", required = false, example = "wanxiaJaneYang"),
                    @Parameter(in = ParameterIn.QUERY,name = "email",description ="email of the trainee",required =false,example ="wanxiayang86@gmailcom")
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully sent reset password email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee username or email not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/reset-password")
    public ResponseEntity<?> sendResetEmail(@RequestParam String username, @RequestParam String email) {
        try {
            if (username != null && !username.isEmpty()) {
                emailService.sendResetPwEmailToTraineeByUsername(username);
            } else if (email != null && !email.isEmpty()) {
                emailService.sendResetPwEmailToTraineeByEmail(email);
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
