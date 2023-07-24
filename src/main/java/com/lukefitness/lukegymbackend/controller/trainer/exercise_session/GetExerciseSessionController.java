package com.lukefitness.lukegymbackend.controller.trainer.exercise_session;

import com.lukefitness.lukegymbackend.models.ExerciseSession;
import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainer/{trainerId}/exercise_session")
@Tag(name="Trainer-Exercise-Session")
public class GetExerciseSessionController {
    @Autowired
    ExerciseSessionService exerciseSessionService;

    @Autowired
    ProgramCardService programCardService;
    @GetMapping("/{cardId}/get")
    @Operation(summary = "get a program card's exercise session",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "cardId", description = "program card id", required = true)
            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Exercise session retrieved successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = List.class),
                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExerciseSession.class))
                            )),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid program card id"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            },
            security = @SecurityRequirement(name = "bearer-key")
    )
    public ResponseEntity<?> getExerciseSessions(@PathVariable Integer cardId, @PathVariable Integer trainerId){
        programCardService.getProgramCard(cardId, trainerId);
        return Response.success(exerciseSessionService.getExerciseSessionByCardId(cardId));
    }

    @GetMapping("/{cardId}/get/{exerciseSessionId}")
    @Operation(
            summary = "get a exercise session",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "cardId", description = "program card id", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "exerciseSessionId", description = "exercise session id", required = true),
                    @Parameter(name = "trainerId", description = "trainer id", required = true)
            },
            security = @SecurityRequirement(name=("bearer-key")),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exercise session retrieved successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExerciseSession.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid program card id or exercise session id"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Exercise session not found")
            }
    )
    public ResponseEntity<?> getExerciseSession(@PathVariable Integer trainerId, @PathVariable Integer cardId, @PathVariable Integer exerciseSessionId){
        programCardService.getProgramCard(cardId, trainerId);
        return Response.success(exerciseSessionService.getExerciseSessionByCardIdAndExerciseSessionId(cardId, exerciseSessionId));
    }
}
