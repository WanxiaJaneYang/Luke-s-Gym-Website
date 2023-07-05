package com.lukefitness.lukegymbackend.exception;

public class KeywordCannotBeNullException extends BadRequestException{
    public KeywordCannotBeNullException(String keyword) {
        super("Keyword cannot be null: " + keyword);
    }
}
