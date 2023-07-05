package com.lukefitness.lukegymbackend.exception;

public class UserNotExistException extends NotFoundException{
    public UserNotExistException() {
        super("User does not exist");
    }
}
