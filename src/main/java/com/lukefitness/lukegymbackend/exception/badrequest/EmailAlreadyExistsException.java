package com.lukefitness.lukegymbackend.exception.badrequest;

import com.lukefitness.lukegymbackend.exception.BadRequestException;

public class EmailAlreadyExistsException extends BadRequestException {
public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}
