package com.lukefitness.lukegymbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraineeFeedbackReq {
    @NotNull(message = "Trainee feedback cannot be null")
    String traineeFeedback;
    @NotNull(message = "Score cannot be null")
    @Positive(message = "Score must be positive")
    Float score;
}
