package com.lukefitness.lukegymbackend.dto.orderby;

public enum ProgramOrderByEnum {
    STATUS("status"),
    START_DATE("startDate"),
    END_DATE("endDate"),
    TRAINER_ID("trainerId"),
    TRAINEE_ID("traineeId");

    private final String value;

    ProgramOrderByEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
