package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNameAlreadyExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResult handle(UserNameAlreadyExistException e) {
        return new ErrorResult(e.getMessage());

    }

    @ExceptionHandler(UserNotfoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResult handle(UserNotfoundException e) {
        return new ErrorResult(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResult handle(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return new ErrorResult(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResult handle(ConstraintViolationException e) {

        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            message.append(constraint.getMessage()).append("; ");
        }

        return new ErrorResult(message.toString());
    }

}
