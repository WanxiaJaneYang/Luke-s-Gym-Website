package com.lukefitness.lukegymbackend.exception.handler;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.utils.Response;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e){
        e.printStackTrace();
        return Response.badRequest(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        System.out.println("handleNotFoundException");
        e.printStackTrace();
        return Response.notFound(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e){
        System.out.println("handleUnauthorizedException");
        e.printStackTrace();
        return Response.unauthorized(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        System.out.println("handleHttpMessageNotReadableException");
        e.printStackTrace();
        return Response.badRequest(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e){
        System.out.println("handleIllegalArgumentException");
        e.printStackTrace();
        return Response.badRequest(e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicateKeyException(DuplicateKeyException e){
        System.out.println("handleDuplicateKeyException");
        e.printStackTrace();
        String message = e.getCause().getMessage();
        int keyNameIndex = message.indexOf("key '") + 5;
        int keyNameEndIndex = message.indexOf("'", keyNameIndex);
        String keyName = message.substring(keyNameIndex, keyNameEndIndex);
        int duplicateKeyIndex = message.indexOf("Duplicate entry '") + 17;
        int duplicateKeyEndIndex = message.indexOf("'", duplicateKeyIndex);
        String duplicateKey = message.substring(duplicateKeyIndex, duplicateKeyEndIndex);
        return Response.badRequest("Key '" + keyName + "' with value '" + duplicateKey + "' already exists");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        System.out.println("handleException");
        e.printStackTrace();
        return Response.internalServerError(e.getMessage());
    }
}
