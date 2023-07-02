package com.lukefitness.lukegymbackend.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException() {
        super("Login failed - username or password is incorrect");
    }
}
