package com.lukefitness.lukegymbackend.controller.admin.trainee;

import com.lukefitness.lukegymbackend.models.request.register.UserRegisterReq;
import com.lukefitness.lukegymbackend.models.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/trainee")
@Tag(name = "Admin-Trainee Controller")
public class AddTraineeController {
    @Autowired
    private TraineeService traineeService;

    @Operation(summary = "add a trainee",
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainee json, including username,email and password",
                        required = true,
                        content = @io.swagger.v3.oas.annotations.media.Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UserRegisterReq.class))))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully add a trainee",content = {@io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TraineeResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> addTrainee(@RequestBody UserRegisterReq traineeRegisterReq) {
        TraineeResponse result=traineeService.traineeRegister(traineeRegisterReq);
        return Response.successCreated("Successfully add a trainee",result);
    }
}
