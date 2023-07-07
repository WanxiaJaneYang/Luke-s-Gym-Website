package com.lukefitness.lukegymbackend.models;

import lombok.Data;

@Data
public class Trainer extends User {
    private boolean is_admin;
}
