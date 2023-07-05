package com.lukefitness.lukegymbackend.exception;

public class UserAlreadyExistsException extends BadRequestException{
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
