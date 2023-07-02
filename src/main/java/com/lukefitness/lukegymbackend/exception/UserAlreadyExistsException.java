package com.lukefitness.lukegymbackend.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
