package com.lukefitness.lukegymbackend.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeContactInfo {
    private int traineeId;
    private String postalCode;
    private String phone;
    private String fullName;
    private String occupation;
    private String address;
    private String state;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private LocalDate dateOfBirth;
}
