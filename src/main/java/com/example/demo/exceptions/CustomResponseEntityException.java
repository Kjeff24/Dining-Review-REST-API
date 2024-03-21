package com.example.demo.exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
public class CustomResponseEntityException extends Throwable {
    private final ResponseEntity<String> responseEntity;

    public CustomResponseEntityException(String message, HttpStatus status) {
        super(message);
        this.responseEntity = ResponseEntity.status(status).body(message);
    }

    public ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }
}
