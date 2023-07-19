package com.lukefitness.lukegymbackend.dto.orderby;

public enum ProgramCardOrderByEnum {
    CARD_ID("card_id"),
    DATE("date"),
    DURATION("duration"),
    TRAINEE_ID("trainee_id"),
    STATUS("status"),
    SESSION_FOCUS_1("session_focus_1"),
    SESSION_FOCUS_2("session_focus_2");

    private final String value;

    ProgramCardOrderByEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
