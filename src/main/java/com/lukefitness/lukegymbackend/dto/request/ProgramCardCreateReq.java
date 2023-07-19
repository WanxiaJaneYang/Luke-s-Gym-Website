package com.lukefitness.lukegymbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgramCardCreateReq {
    private Date date;

    private Integer duration;

    private Integer traineeId;

    private String sessionFocus1;

    private String sessionFocus2;
}
