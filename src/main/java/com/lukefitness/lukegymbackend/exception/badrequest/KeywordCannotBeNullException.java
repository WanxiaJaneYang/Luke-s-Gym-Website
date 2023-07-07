package com.lukefitness.lukegymbackend.exception.badrequest;

import com.lukefitness.lukegymbackend.exception.BadRequestException;

public class KeywordCannotBeNullException extends BadRequestException {
    public KeywordCannotBeNullException(String keyword) {
        super("Keyword cannot be null: " + keyword);
    }
}
