package com.lukefitness.lukegymbackend.controller.admin.trainer;

import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/trainer")
@Tag(name = "Admin - Trainer")
public class DeleteTrainerController {
    @Autowired
    TrainerService trainerService;
    @DeleteMapping("/{trainerId}")
    @Operation(summary= "delete a trainer account",
            security = @SecurityRequirement(name = "Bearer-key"),
            parameters = @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Trainer's id", required = true),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
            })
    public ResponseEntity<?> deleteTrainer(@PathVariable int trainerId){
        trainerService.deactivateTrainer(trainerId);
        return Response.success();
    }
}
