package com.lukefitness.lukegymbackend.controller.login;

import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.models.request.register.TrainerRegisterReq;
import com.lukefitness.lukegymbackend.models.request.register.UserRegisterReq;
import com.lukefitness.lukegymbackend.models.response.login.TrainerLoginResponse;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login/trainer")
@Tag(name = "Login/Register Controller")
public class TrainerLoginController {
    @Autowired
    TrainerService trainerService;

    @Operation(summary = "Login as a trainer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "username/email and password",
                    required = true,
                    content=@Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value ="{\"username\":\"trainer1\",\"password\":\"123456\"}"
                            )))
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in as a trainer",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TrainerLoginResponse.class),
                            examples = @ExampleObject(value = "{\"data\": {\"id\": 14,\"email\": \"trainer1@example.com\",\"username\": \"trainer1\",\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InRyYWluZXIxIiwidXNlclJvbGUiOiJmYWxzZSIsInVzZXJJZCI6IjE0IiwiZXhwIjoxNjg4NzEyOTIwfQ.PeR53r_sHMhCTx82vlar7bHHvXmT9p_YSHcKp8UWkn4\"}}")
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Trainer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> trainerLogin(@RequestBody TrainerRegisterReq trainerLoginReq){
        TrainerLoginResponse response;
        if (trainerLoginReq.getPassword()==null){
            return Response.badRequest("Password cannot be null");
        }
        if (trainerLoginReq.getEmail()==null && trainerLoginReq.getUsername()==null){
            return Response.badRequest("Email or username cannot be null");
        }
        if(trainerLoginReq.getUsername()==null){
            response = trainerService.trainerLoginByEmail(trainerLoginReq.getEmail(), trainerLoginReq.getPassword());
        }else{
            response = trainerService.trainerLogin(trainerLoginReq.getUsername(), trainerLoginReq.getPassword());
        }
        return Response.success(response);
    }
}
