package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserNameAlreadyExistException extends RuntimeException{
    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
