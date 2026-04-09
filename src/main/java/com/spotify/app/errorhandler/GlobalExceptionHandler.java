package com.spotify.app.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(Exception ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "User not found with id: " + ex.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Map<String, String>> handleGenericRestError(RestClientException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "REST Call to Spotify Failed: " + ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, String>> handleClientError(HttpClientErrorException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "REST Call to Spotify Failed: " + ex.getMessage());

        return new ResponseEntity<>(responseBody, ex.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Map<String, String>> handleServerError(HttpServerErrorException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "REST Call to Spotify Failed: " + ex.getMessage());

        return new ResponseEntity<>(responseBody, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Some Error Occurred: " + ex.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
