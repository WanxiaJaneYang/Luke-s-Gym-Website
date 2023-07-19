package com.lukefitness.lukegymbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseSessionReq {
    private String name;

    private String sessionType;

    private Integer time;

    private Integer reps;

    private Integer sets;

    private String intensity;

    private String tempo;

    private Integer rest;
}
