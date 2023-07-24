package com.lukefitness.lukegymbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    private Integer programId;

    private Integer cardId;

    private Boolean attendance;

    private Boolean feedbackGiven;

    private Date startDate;

    private Date endDate;

    private String status;

    private Integer traineeId;

    private Integer trainerId;

    public Program(ProgramCard programCard) {
        this.cardId = programCard.getCardId();
        this.traineeId = programCard.getTraineeId();
        this.trainerId = programCard.getTrainerId();
        this.startDate = programCard.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(programCard.getDate());
        calendar.add(Calendar.MINUTE, programCard.getDuration());
        this.endDate = calendar.getTime();
    }

}