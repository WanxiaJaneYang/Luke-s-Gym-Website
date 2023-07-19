package com.lukefitness.lukegymbackend.controller.trainer.exercise_session;

import com.lukefitness.lukegymbackend.dto.request.ExerciseSessionReq;
import com.lukefitness.lukegymbackend.models.ExerciseSession;
import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/{cardId}/exercise_session")
@Tag(name="Trainer-Exercise-Session")
public class EditExerciseSessionController {
    @Autowired
    ExerciseSessionService exerciseSessionService;
    @PutMapping("/{exerciseSessionId}")
    @Operation(
            security = @SecurityRequirement(name = "bearer-key"),
            summary = "edit a exercise session",
            parameters = {
                    @Parameter(name = "trainerId", description = "trainer id", required = true),
                    @Parameter(name = "cardId", description = "program card id", required = true),
                    @Parameter(name = "exerciseSessionId", description = "exercise session id", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "exercise session details", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExerciseSessionReq.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exercise session updated successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Invalid Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Card not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> editExerciseSession(@PathVariable Integer cardId, @PathVariable Integer trainerId, @PathVariable Integer exerciseSessionId, @RequestBody ExerciseSessionReq exerciseSessionReq){
        ExerciseSession exerciseSession =new ExerciseSession(exerciseSessionReq);
        exerciseSession.setCardId(cardId);
        exerciseSession.setExerciseSessionId(exerciseSessionId);
        exerciseSessionService.updateExerciseSession(exerciseSession);
        return Response.success();
    }
}
