package com.lukefitness.lukegymbackend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrainerFeedbackReq {
    @NotEmpty(message = "Trainer feedback cannot be empty")
    @NotNull(message = "Trainer feedback cannot be null")
    String trainerFeedback;

    public String getTrainerFeedback() {
        return trainerFeedback;
    }
}
