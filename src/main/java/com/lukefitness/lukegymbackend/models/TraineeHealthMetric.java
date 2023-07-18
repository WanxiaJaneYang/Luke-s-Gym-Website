package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraineeHealthMetric {
    private Integer traineeId;

    private Boolean isExercising;

    private Integer exerciseFrequency;

    private Integer exerciseDuration;

    private String exerciseIntensity;

    private Integer restingHeartRate;

    private BigDecimal waistGirth;

    private BigDecimal whRatio;

    private String restingBloodPressure;

    private BigDecimal hipGirth;

    private BigDecimal bmi;

    private String apssStage1Result;

    private Boolean isPregnant;

    private Date createdAt;

    private Date updatedAt;

    private String jobAcitiveLevel;

    private String dayActiveLevel;

    private String sleepHours;

    private String physicallyRestrictedFrequency;

    private String emotionalAffectedFrequency;

    private Boolean nutritionConsultationRequired;

    private Date apssResultDate;

    private String pregnancyDetails;

    private String medication;

    private String dietDescription;

    private String medicalConcern;

    public TraineeHealthMetric(int id) {
        this.traineeId = id;
    }
}