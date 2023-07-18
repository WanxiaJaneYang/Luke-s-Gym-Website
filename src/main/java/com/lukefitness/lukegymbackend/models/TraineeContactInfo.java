package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeContactInfo {
    private Integer traineeId;

    private String fullName;

    private String address;

    private String occupation;

    private Date dateOfBirth;

    private String phone;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private Date createdAt;

    private Date updatedAt;

    private String state;

    private String postalCode;

    public TraineeContactInfo(int traineeId){
        this.traineeId=traineeId;
    }
}