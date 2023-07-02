package com.lukefitness.lukegymbackend.models;

import lombok.Data;

@Data
public class Trainer {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private boolean is_admin;

}
