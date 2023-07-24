package com.lukefitness.lukegymbackend.models;

import com.lukefitness.lukegymbackend.dto.request.ExerciseSessionReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseSession {
    private Integer exerciseSessionId;

    private String name;

    private Integer cardId;

    private String sessionType;

    private Integer time;

    private Integer reps;

    private Integer sets;

    private String intensity;

    private String tempo;

    private Integer rest;
    private Timestamp updateAt;

    public ExerciseSession(ExerciseSessionReq exerciseSessionReq) {
        this.name = exerciseSessionReq.getName();
        this.sessionType = exerciseSessionReq.getSessionType();
        this.time = exerciseSessionReq.getTime();
        this.reps = exerciseSessionReq.getReps();
        this.sets = exerciseSessionReq.getSets();
        this.intensity = exerciseSessionReq.getIntensity();
        this.tempo = exerciseSessionReq.getTempo();
        this.rest = exerciseSessionReq.getRest();
    }
}