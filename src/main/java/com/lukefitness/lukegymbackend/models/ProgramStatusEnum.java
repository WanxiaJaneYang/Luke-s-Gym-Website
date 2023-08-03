package com.lukefitness.lukegymbackend.models;

public enum ProgramStatusEnum {
    PAST("past"),
    UPCOMING("upcoming"),
    CANCELLED("cancelled");

    private String value;

    ProgramStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
