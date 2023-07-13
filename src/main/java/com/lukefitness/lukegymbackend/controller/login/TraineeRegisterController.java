package com.lukefitness.lukegymbackend.controller.login;

import com.lukefitness.lukegymbackend.dto.request.register.UserRegisterReq;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
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

@RestController
@RequestMapping("/register/trainee")
@Tag(name = "Login/Register Controller")
public class TraineeRegisterController {
    @Autowired
    TraineeService traineeService;

    @Operation(summary = "register a trainee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainee json, including username and password",
                    required = true,
                    content=@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRegisterReq.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"username\":\"trainee1\",\"email\":\"trainee@example.com\",\"password\":\"123456\"}"
                            ))))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully register a trainee",content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TraineeResponse.class),
                    examples =@ExampleObject(value= "{\"data\": {\"username\": \"trainee6\",\"id\": 7}}") )}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> traineeRegister(@RequestBody UserRegisterReq trainee) {
        TraineeResponse response = traineeService.traineeRegister(trainee);
        return Response.successCreated(response);
    }
}
