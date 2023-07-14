package com.lukefitness.lukegymbackend.controller.trainer;

import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trainer/{trainerId}/")
@Tag(name = "Trainer-Account Controller")
public class TrainerDeleteController {
    @Autowired
    TrainerService trainerService;

    @Autowired
    EmailService emailService;

    @Operation(summary = "Delete trainer by trainer id",
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully deleted trainer"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainer not found")
            }
    )
    @DeleteMapping
    public ResponseEntity<?> deleteTrainer(@PathVariable int trainerId) throws NotFoundException {
        Trainer trainer= trainerService.getTrainerById(trainerId);
        if (trainer == null)
            throw new NotFoundException("Trainer not found");

        trainerService.deactivateTrainer(trainerId);
        emailService.sendTrainerDeletionEmail(trainer);
        return ResponseEntity.ok().build();
    }
}
