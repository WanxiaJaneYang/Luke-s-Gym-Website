package com.lukefitness.lukegymbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseReq {
    @NotNull(message = "Exercise name cannot be null")
    String name;
}
