package com.lukefitness.lukegymbackend.exception;

import com.lukefitness.lukegymbackend.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        System.out.println("handleException");
        e.printStackTrace();
        return Response.internalServerError(e.getMessage());
    }
}
