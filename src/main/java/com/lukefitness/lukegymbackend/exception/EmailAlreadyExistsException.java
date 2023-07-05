package com.lukefitness.lukegymbackend.exception;

public class EmailAlreadyExistsException extends BadRequestException {
public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}
