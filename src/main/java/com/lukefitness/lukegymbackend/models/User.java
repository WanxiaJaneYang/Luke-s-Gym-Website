package com.lukefitness.lukegymbackend.models;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean email_verified;
}
