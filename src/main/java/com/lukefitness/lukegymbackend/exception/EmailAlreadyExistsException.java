package com.lukefitness.lukegymbackend.exception;

public class EmailAlreadyExistsException extends RuntimeException {
public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}
