package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private Integer id;

    private String name;

    private Date createAt;

    private Date updateAt;

    public Exercise(String name) {
            this.name = name;
    }
}