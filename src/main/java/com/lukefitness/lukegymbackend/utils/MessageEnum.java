package com.lukefitness.lukegymbackend.utils;

public enum MessageEnum {
    SUCCESS("success"),
    USER_ALREADY_EXISTS("user already exists"),
    USER_NOT_EXISTS("user not exists"),
    LOGIN_SUCCESS("login success"),
    LOGIN_FAIL("wrong username or password");

    private String message;

    private MessageEnum(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }
}
