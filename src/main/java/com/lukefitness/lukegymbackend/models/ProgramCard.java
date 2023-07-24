package com.lukefitness.lukegymbackend.models;

import com.lukefitness.lukegymbackend.dto.request.ProgramCardCreateReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramCard {
    private Integer cardId;

    private Integer trainerId;

    private Timestamp date;

    private Integer duration;

    private Integer traineeId;

    private String status;

    private String sessionFocus1;

    private String sessionFocus2;

    private Timestamp sentAt;

    private Timestamp updateAt;

    public ProgramCard(ProgramCardCreateReq programCardCreateReq) {
            this.date = programCardCreateReq.getDate();
            this.duration = programCardCreateReq.getDuration();
            this.traineeId = programCardCreateReq.getTraineeId();
            this.sessionFocus1 = programCardCreateReq.getSessionFocus1();
            this.sessionFocus2 = programCardCreateReq.getSessionFocus2();
    }

    public ProgramCard(Integer cardId) {
        this.cardId = cardId;
    }
}