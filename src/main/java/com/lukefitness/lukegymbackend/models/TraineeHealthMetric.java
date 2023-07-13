package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeHealthMetric {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.trainee_id
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private Integer traineeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.is_exercising
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private Boolean isExercising;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.exercise_frequency
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private Integer exerciseFrequency;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.exercise_duration
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private Integer exerciseDuration;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.exercise_intensity
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private String exerciseIntensity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.resting_heart_rate
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private Integer restingHeartRate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.waist_girth
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private BigDecimal waistGirth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.wh_ratio
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private BigDecimal whRatio;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.resting_blood_pressure
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private String restingBloodPressure;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.hip_girth
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private BigDecimal hipGirth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.bmi
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private BigDecimal bmi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.apss_stage_1_result
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private String apssStage1Result;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.is_pregnant
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private Boolean isPregnant;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.created_at
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.updated_at
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private Date updatedAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.pregnancy_details
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private String pregnancyDetails;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trainee_health_metrics.medication
     *
     * @mbg.generated Wed Jul 12 19:28:51 ACST 2023
     */
    private String medication;

    public TraineeHealthMetric(int id) {
    }

}