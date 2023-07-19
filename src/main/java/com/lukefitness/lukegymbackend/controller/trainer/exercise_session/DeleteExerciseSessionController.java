package com.lukefitness.lukegymbackend.controller.trainer.exercise_session;

import com.lukefitness.lukegymbackend.service.ExerciseSessionService;
import com.lukefitness.lukegymbackend.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer/{trainerId}/{cardId}/exercise_session")
public class DeleteExerciseSessionController {
    @Autowired
    ExerciseSessionService exerciseSessionService;
    @DeleteMapping("/{exerciseSessionId}")
    public ResponseEntity<?> deleteExerciseSession(@PathVariable Integer cardId, @PathVariable Integer trainerId, @PathVariable Integer exerciseSessionId){
        exerciseSessionService.deleteExerciseSession(exerciseSessionId);
        return Response.success();
    }
}
