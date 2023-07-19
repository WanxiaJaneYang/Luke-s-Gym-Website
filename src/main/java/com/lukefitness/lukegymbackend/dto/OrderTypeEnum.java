package com.lukefitness.lukegymbackend.dto;

public enum OrderTypeEnum {
    ASC("asc"),
    DESC("desc");

    private String value;

    OrderTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
