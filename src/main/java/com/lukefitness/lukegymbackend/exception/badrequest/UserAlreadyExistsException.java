package com.lukefitness.lukegymbackend.exception.badrequest;

import com.lukefitness.lukegymbackend.exception.BadRequestException;

public class UserAlreadyExistsException extends BadRequestException {
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
