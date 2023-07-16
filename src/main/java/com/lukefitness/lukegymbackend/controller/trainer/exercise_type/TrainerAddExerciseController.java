package com.lukefitness.lukegymbackend.controller.trainer.exercise_type;
import com.lukefitness.lukegymbackend.dto.request.ExercisePostReq;
import com.lukefitness.lukegymbackend.service.ExerciseService;
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
@RequestMapping("/trainer/{trainerId}/exercise")
@Tag(name="trainer/exercise")
public class TrainerAddExerciseController {
    @Autowired
    ExerciseService exerciseService;
    @PostMapping("/add")
    @Operation(summary="add exercise for trainer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "exercise name", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExercisePostReq.class))),
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Exercise added successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid exercise name"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            }
    )
    public ResponseEntity<?> addExercise(@RequestBody ExercisePostReq exercise){
        exerciseService.addExercise(exercise.getName());
        return Response.success();
    }
}
