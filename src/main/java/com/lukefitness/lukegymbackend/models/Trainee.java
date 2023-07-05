package com.lukefitness.lukegymbackend.models;

import lombok.Data;

@Data
public class Trainee {
    private int id;
    private int trainerId;
    private String username;
    private String password;
    private String email;
    private boolean emailVerified;
}
