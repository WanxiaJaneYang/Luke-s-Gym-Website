package com.lukefitness.lukegymbackend.controller.admin.exercise;

import com.lukefitness.lukegymbackend.service.ExerciseService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class contains the endpoints for deleting exercise for admin.
 * The exercise id is required to delete the exercise.
 *
 */
@RestController
@RequestMapping("/admin/exercise")
@Tag(name="Admin-Exercise")
public class DeleteExerciseController {
    @Autowired
    ExerciseService exerciseService;

    @DeleteMapping("/{id}/delete")
    @Operation(summary="delete exercise for admin",
            security = @SecurityRequirement(name="bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name="id", description="exercise id", required=true)
            },
            responses = {
            @ApiResponse(responseCode = "200", description = "Exercise deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid exercise id"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            }
    )
    public ResponseEntity<?> deleteExercise(@PathVariable Integer id) {
        exerciseService.deleteExercise(id);
        return Response.success();
    }
}
