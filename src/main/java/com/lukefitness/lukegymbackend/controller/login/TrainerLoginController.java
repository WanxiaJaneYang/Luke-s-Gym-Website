package com.lukefitness.lukegymbackend.controller.login;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
@RestController
@RequestMapping("/trainer")
@Tag(name = "Login/Register Controller")
public class TrainerLoginController {
    @Autowired
    TrainerService trainerService;
    @PostMapping("/login")
    @Operation(summary = "Login as a trainer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainer json, including username and password",
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
                            examples = @ExampleObject(value = "{\"data\": {\"id\": 14,\"email\": \"trainer1@example.com\",\"username\": \"trainer1\",\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InRyYWluZXIxIiwidXNlclJvbGUiOiJmYWxzZSIsInVzZXJJZCI6IjE0IiwiZXhwIjoxNjg4NzEyOTIwfQ.PeR53r_sHMhCTx82vlar7bHHvXmT9p_YSHcKp8UWkn4\"}}")
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Trainer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity trainerLogin(@RequestBody Trainer trainer){
        try {
            Trainer trainerTemp = trainerService.trainerLogin(trainer.getUsername(), trainer.getPassword());
            String token = JWTUtils.getToken(String.valueOf(trainerTemp.getId()), trainerTemp.getUsername(), String.valueOf(trainerTemp.is_admin()));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", trainerTemp.getId());
            resultMap.put("username", trainerTemp.getUsername());
            resultMap.put("email", trainerTemp.getEmail());
            resultMap.put("token", token);
            return Response.success(resultMap);
        }catch (BadRequestException e) {
            return Response.error(HttpStatus.UNAUTHORIZED, e.getMessage());
        }catch (NotFoundException e){
            return Response.notFound(e.getMessage());
        }catch (Exception e){
            return Response.internalServerError(e.getMessage());
        }
    }
}