package com.lukefitness.lukegymbackend.controller.trainer.exercise_session;

import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer/{trainerId}/{cardId}/exercise_session")
@Tag(name="Trainer-Exercise-Session")
public class GetController {
    @Autowired
    ExerciseSessionService exerciseSessionService;
    @GetMapping
    @Operation(summary = "get the program card's exercise session",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "cardId", description = "program card id", required = true)
            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Exercise session retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid program card id"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            },
            security = @SecurityRequirement(name = "bearer-key")
    )
    public ResponseEntity<?> getExerciseSessions(@PathVariable Integer cardId){
        return Response.success(exerciseSessionService.getExerciseSessionByCardId(cardId));
    }
}
