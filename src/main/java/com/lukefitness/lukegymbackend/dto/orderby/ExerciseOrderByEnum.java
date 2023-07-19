package com.lukefitness.lukegymbackend.dto.orderby;

public enum ExerciseOrderByEnum {
    NAME("name"),
    CREATE_AT("create_at"),
    UPDATE_AT("update_at");

    private String value;

    ExerciseOrderByEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
