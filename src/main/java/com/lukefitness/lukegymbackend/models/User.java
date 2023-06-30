package com.lukefitness.lukegymbackend.models;

import org.hibernate.usertype.UserType;

import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // In a real application, make sure to hash this
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType; // Enum UserType will be created separately

}
