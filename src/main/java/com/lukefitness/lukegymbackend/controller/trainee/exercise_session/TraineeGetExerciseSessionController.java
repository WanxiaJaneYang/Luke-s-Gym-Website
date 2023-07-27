package com.lukefitness.lukegymbackend.controller.trainee.exercise_session;

import com.lukefitness.lukegymbackend.models.ExerciseSession;
import com.lukefitness.lukegymbackend.models.ProgramCardExample;
import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@RequestMapping("/trainee/{traineeId}/exercise_session")
@Tag(name="Trainee-Exercise-Session")
public class TraineeGetExerciseSessionController {
    @Autowired
    ProgramCardService programCardService;

    @Autowired
    ExerciseSessionService exerciseSessionService;
    @GetMapping("/{cardId}/get")
    @Operation(summary = "get a program card's exercise session",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "cardId", description = "program card id", required = true,in = ParameterIn.PATH),
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "trainee id", required = true,in = ParameterIn.PATH)
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
    public ResponseEntity<?> getExerciseSessions(@PathVariable Integer cardId, @PathVariable Integer traineeId){
        ProgramCardExample example = new ProgramCardExample();
        example.createCriteria().andTraineeIdEqualTo(traineeId).andCardIdEqualTo(cardId);
        programCardService.getProgramCardByExample(example);
        return Response.success(exerciseSessionService.getExerciseSessionByCardId(cardId));
    }
}
