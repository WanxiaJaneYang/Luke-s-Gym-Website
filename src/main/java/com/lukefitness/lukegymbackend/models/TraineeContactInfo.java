package com.lukefitness.lukegymbackend.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeContactInfo {
    private int trainee_id;
    private String postal_code;
    private String phone;
    private String full_name;
    private String occupation;
    private String address;
    private String state;
    private String emergency_contact_name;
    private String emergency_contact_phone;
    private LocalDate date_of_birth;
}
