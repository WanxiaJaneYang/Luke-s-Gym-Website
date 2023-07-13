package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeFitnessGoal {

    private Integer traineeId;

    private String jobActivityLevel;

    private String exerciseDays;

    private String preferredExerciseIntensity;

    private Date createdAt;

    private Date updatedAt;

    private String number1Goal;

    private String otherGoals;

    private String successPlan;

    private String currentExercisePlan;

    private String fitnessInterests;

    private String dislikedFitnessActivities;

    public TraineeFitnessGoal(int id) {
        this.traineeId = id;
    }
}