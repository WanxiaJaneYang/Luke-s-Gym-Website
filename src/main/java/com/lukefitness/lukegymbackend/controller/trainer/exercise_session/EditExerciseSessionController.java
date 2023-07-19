package com.lukefitness.lukegymbackend.controller.trainer.exercise_session;

import com.lukefitness.lukegymbackend.dto.request.ExerciseSessionReq;
import com.lukefitness.lukegymbackend.models.ExerciseSession;
import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import com.lukefitness.lukegymbackend.utils.Response;
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
    public ResponseEntity<?> editExerciseSession(@PathVariable Integer cardId, @PathVariable Integer trainerId, @PathVariable Integer exerciseSessionId, @RequestBody ExerciseSessionReq exerciseSessionReq){
        exerciseSessionService.updateExerciseSession(exerciseSessionReq, exerciseSessionId);
        return Response.success();
    }
}
