package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int id;
    String username;
    String password;
    String email;
    boolean email_verified;
    boolean is_active;
    Timestamp deactivation_date;
    Timestamp last_used_date;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.email_verified = true;
    }
}
