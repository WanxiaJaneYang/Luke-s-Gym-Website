package com.lukefitness.lukegymbackend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Response extends ResponseEntity {
    public Response(HttpStatusCode status) {
        super(status);
    }

    public static ResponseEntity success(Object data) {
        Map<String, Object> response = Map.of("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity successCreated(Object data) {
        Map<String, Object> response = Map.of("data", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static ResponseEntity error(HttpStatus status, String message) {
        Map<String, Object> response = Map.of("error", message);
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity badRequest(String message) {
        return error(HttpStatus.BAD_REQUEST, message);
    }

    public static ResponseEntity notFound(String message) {
        return error(HttpStatus.NOT_FOUND, message);
    }

    public static ResponseEntity internalServerError(String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}
