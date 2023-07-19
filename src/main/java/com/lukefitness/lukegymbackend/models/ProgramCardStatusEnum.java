package com.lukefitness.lukegymbackend.models;

public enum ProgramCardStatusEnum {
    CANCELLED("cancelled"),
    LOG("log"),
    DRAFT("draft"),
    SENT("sent");

    private final String value;

    ProgramCardStatusEnum(String status) {
        this.value = status;
    }

    public String getValue() {
        return value;
    }
}
