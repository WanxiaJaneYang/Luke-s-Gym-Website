package com.lukefitness.lukegymbackend.controller.admin.trainee;

import com.lukefitness.lukegymbackend.dto.request.Email;
import com.lukefitness.lukegymbackend.dto.request.Password;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/trainee/{traineeId}")
@Tag(name = "Admin-Trainee Controller")
public class updateTraineeController {
    @Autowired
    private TraineeService traineeService;

    @Operation(
            summary = "update a trainee's email by id",
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "trainee id", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainee email", required = true),
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully update a trainee's email"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PatchMapping("/email")
    public ResponseEntity<?> updateTraineeEmail(@PathVariable int traineeId, @RequestBody Email email) {
        traineeService.updateTraineeEmail(traineeId, email.getEmail());
        return Response.success();
    }

    @Operation(
            summary = "update a trainee's password by id",
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "trainee id", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainee password", required = true),
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully update a trainee's password"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PatchMapping("/password")
    public ResponseEntity<?> updateTraineePassword(@PathVariable int traineeId, @RequestBody Password password) {
        traineeService.updateTraineePassword(traineeId, password.getPassword());
        return Response.success();
    }
}
