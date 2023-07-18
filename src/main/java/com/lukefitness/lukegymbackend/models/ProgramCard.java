package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramCard {
    private Integer cardId;

    private Integer trainerId;

    private Date date;

    private Integer duration;

    private Integer traineeId;

    private String status;

    private String sessionFocus1;

    private String sessionFocus2;

}