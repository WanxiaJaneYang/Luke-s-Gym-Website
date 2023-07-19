package com.lukefitness.lukegymbackend.models;

import com.lukefitness.lukegymbackend.dto.request.ProgramCardCreateReq;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer traineeId) {
        this.traineeId = traineeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionFocus1() {
        return sessionFocus1;
    }

    public void setSessionFocus1(String sessionFocus1) {
        this.sessionFocus1 = sessionFocus1;
    }

    public String getSessionFocus2() {
        return sessionFocus2;
    }

    public void setSessionFocus2(String sessionFocus2) {
        this.sessionFocus2 = sessionFocus2;
    }

    public ProgramCard(ProgramCardCreateReq programCardCreateReq) {
        this.date = programCardCreateReq.getDate();
        this.duration = programCardCreateReq.getDuration();
        this.traineeId = programCardCreateReq.getTraineeId();
        this.sessionFocus1 = programCardCreateReq.getSessionFocus1();
        this.sessionFocus2 = programCardCreateReq.getSessionFocus2();
    }
}