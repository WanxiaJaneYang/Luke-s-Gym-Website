package com.lukefitness.lukegymbackend.controller.login;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.request.register.UserRegisterReq;
import com.lukefitness.lukegymbackend.models.response.login.TraineeLoginResponse;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login/trainee")
@Tag(name = "Login/Register Controller")
public class TraineeLoginController {
    @Autowired
    TraineeService traineeService;

    @Operation(summary = "Login as a trainee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainee json, including username/email and password",
                    required = true,
                    content=@Content(
                            schema=@Schema(implementation = UserRegisterReq.class),
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"username\":\"trainee5\",\"password\":\"123456\"}"
                    ))))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Successfully login as a trainee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(value="{\"data\": {\"username\": \"trainee5\",\"id\": \"0\",\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InRyYWluZWU1IiwidXNlclJvbGUiOiJ0cmFpbmVlIiwidXNlcklkIjoiNCIsImV4cCI6MTY4ODcxMTI3N30.9KHPPD024GY1Tn11sTaoY365GSrPW_dCYH4pMPI-SNY\"}}")
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> traineeLogin(@RequestBody UserRegisterReq trainee) {
        TraineeLoginResponse response;
        if(trainee.getPassword()==null)
            throw new BadRequestException("Password is required");
        if (trainee.getUsername() == null && trainee.getEmail() == null) {
            throw new BadRequestException("Username or password are required");
        }else if (trainee.getEmail() == null) {
             response = traineeService.traineeLogin(trainee.getUsername(), trainee.getPassword());
        }else{
            response=traineeService.traineeLoginByEmail(trainee.getEmail(), trainee.getPassword());
        }
        return Response.success(response);
    }

}
