package com.lukefitness.lukegymbackend.controller.trainer.exercise_session;

import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer/{trainerId}/{cardId}/exercise_session")
@Tag(name="Trainer-Exercise-Session")
public class DeleteExerciseSessionController {
    @Autowired
    ExerciseSessionService exerciseSessionService;
    @DeleteMapping("/{exerciseSessionId}")
    @Operation(
            summary = "delete the program card's exercise session",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "cardId", description = "program card id", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "exerciseSessionId", description = "exercise session id", required = true),
                    @Parameter(name = "trainerId", description = "trainer id", required = true)
            },
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exercise session deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid program card id"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Exercise session not found")
            }
    )
    public ResponseEntity<?> deleteExerciseSession(@PathVariable Integer cardId, @PathVariable Integer trainerId, @PathVariable Integer exerciseSessionId){
        exerciseSessionService.deleteExerciseSession(exerciseSessionId);
        return Response.success();
    }
}
