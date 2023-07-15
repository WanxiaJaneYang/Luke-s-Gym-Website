package com.lukefitness.lukegymbackend.controller.admin.trainer;


import com.lukefitness.lukegymbackend.dto.request.Email;
import com.lukefitness.lukegymbackend.dto.request.Password;
import com.lukefitness.lukegymbackend.dto.request.Username;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/trainer")
@Tag(name = "Admin - Trainer")
public class EditTrainerController {
    @Autowired
    private TrainerService trainerService;

    @Operation(summary = "Edit trainer's username",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters={
                    @io.swagger.v3.oas.annotations.Parameter(name="trainerId", description="Trainer's id", required=true)
            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PatchMapping("/{trainerId}/username")
    public ResponseEntity<?> editTrainerName(@PathVariable int trainerId, @RequestBody Username username){
        trainerService.updateTrainerUsername(trainerId, username.getUsername());
        return Response.success();
    }

    @PatchMapping("/{trainerId}/password")
    @Operation(summary = "method to update the trainer's password",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                @Parameter(name = "trainerId", description = "trainer's id", required = true)
            },
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public ResponseEntity<?> editTrainerPassword(@PathVariable int trainerId, @RequestBody Password password){
        trainerService.updateTrainerPassword(trainerId, password.getPassword());
        return Response.success();
    }

    @PatchMapping("/{trainerId}/email")
    @Operation(summary = "method to edit the trainer's email",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                @Parameter(name = "trainerId", description = "trainer's id", required = true)},
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> editTrainerEmail(@PathVariable int trainerId, @RequestBody Email email){
        trainerService.updateTrainerEmail(trainerId, email.getEmail());
        return Response.success();
    }
}
