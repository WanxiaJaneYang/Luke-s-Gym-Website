package com.lukefitness.lukegymbackend.controller.trainer.exercise_session;

import com.lukefitness.lukegymbackend.dto.request.ExerciseSessionReq;
import com.lukefitness.lukegymbackend.models.ExerciseSession;
import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/{cardId}/exercise_session")
@Tag(name="Trainer-Exercise-Session")
public class PostExerciseSessionController {
    @Autowired
    ExerciseSessionService exerciseSessionService;
    @PostMapping
    @Operation(
            summary = "add an exercise session to the program card",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "cardId", description = "program card id", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "trainer id", required = true),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "exercise session details", required = true, content =
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExerciseSessionReq.class))
                    ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exercise session added successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExerciseSession.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Card not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> addExerciseSession(@PathVariable Integer cardId, @PathVariable Integer trainerId,  @RequestBody ExerciseSessionReq exerciseSessionReq){
        ExerciseSession exerciseSession =new ExerciseSession(exerciseSessionReq);
        exerciseSession.setCardId(cardId);
        return Response.success(exerciseSessionService.insertExerciseSession(exerciseSession));
    }
}
