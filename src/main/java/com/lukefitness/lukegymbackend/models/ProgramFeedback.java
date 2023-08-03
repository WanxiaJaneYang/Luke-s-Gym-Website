package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgramFeedback {
    private Integer feedbackId;

    private Integer programId;

    private Float score;

    private String traineeFeedback;

    private String trainerFeedback;

    public ProgramFeedback(Integer programId) {
        this.programId = programId;
    }
}