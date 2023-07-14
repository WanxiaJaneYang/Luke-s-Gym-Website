package com.lukefitness.lukegymbackend.controller.trainee.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/{traineeId}/link-trainer")
public class UpdateLinkedTrainer {

    @PatchMapping
    public ResponseEntity<?> updateLinkedTrainer(@PathVariable int traineeId, @RequestParam int trainerId){
        return null;
    }
}
