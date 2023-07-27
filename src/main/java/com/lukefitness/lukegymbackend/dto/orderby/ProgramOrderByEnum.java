package com.lukefitness.lukegymbackend.dto.orderby;

public enum ProgramOrderByEnum {
    STATUS("status"),
    START_DATE("start_date"),
    END_DATE("end_date"),
    TRAINER_ID("trainer_id"),
    TRAINEE_ID("trainee_id");

    private final String value;

    ProgramOrderByEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
