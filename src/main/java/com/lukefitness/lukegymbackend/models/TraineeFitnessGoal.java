package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TraineeFitnessGoal {
    private Integer traineeId;

    private Date achieveByDate;

    private String jobActivityLevel;

    private String exerciseDays;

    private String preferredExerciseIntensity;

    private Date createdAt;

    private Date updatedAt;

    private String preferredExerciseTime;

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