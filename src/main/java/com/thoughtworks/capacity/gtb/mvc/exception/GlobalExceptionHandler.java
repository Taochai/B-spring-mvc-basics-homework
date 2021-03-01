package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNameAlreadyExistException.class)
    public ResponseEntity<ErrorResult> handle(UserNameAlreadyExistException e) {
        int httpStatusCode = HttpStatus.BAD_REQUEST.value();
        ErrorResult errorResult = new ErrorResult(httpStatusCode, e.getMessage());
        return ResponseEntity.status(httpStatusCode).body(errorResult);
    }

    @ExceptionHandler(UserNotfoundException.class)
    public ResponseEntity<ErrorResult> handle(UserNotfoundException e) {
        int httpStatusCode = HttpStatus.NOT_FOUND.value();
        ErrorResult errorResult = new ErrorResult(httpStatusCode, e.getMessage());
        return ResponseEntity.status(httpStatusCode).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        int httpStatusCode = HttpStatus.BAD_REQUEST.value();
        ErrorResult errorResult = new ErrorResult(httpStatusCode, message);
        return ResponseEntity.status(httpStatusCode).body(errorResult);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResult> handle(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            message.append(constraint.getMessage()).append("; ");
        }
        int httpStatusCode = HttpStatus.BAD_REQUEST.value();
        ErrorResult errorResult = new ErrorResult(httpStatusCode, message.toString());
        return ResponseEntity.status(httpStatusCode).body(errorResult);
    }

}
