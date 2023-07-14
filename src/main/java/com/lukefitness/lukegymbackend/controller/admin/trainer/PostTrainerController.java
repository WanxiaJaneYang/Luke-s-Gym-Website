package com.lukefitness.lukegymbackend.controller.admin.trainer;

import com.lukefitness.lukegymbackend.dto.request.register.TrainerRegisterReq;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/trainer")
@Tag(name = "Admin - Trainer")
public class PostTrainerController {
    @Autowired
    TrainerService trainerService;
    @PostMapping
    @Operation(summary = "add a trainer account",
            security = @SecurityRequirement(name = "bearer-key"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Trainer's information, note: is_admin is always false no matter what you send",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TrainerRegisterReq.class))
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<?> createTrainer(@RequestBody TrainerRegisterReq trainerRegisterReq){
        trainerService.registerTrainer(trainerRegisterReq);
        return Response.success();
    }
}
