package com.thoughtworks.capacity.gtb.mvc;

public class UserNameAlreadyExistException extends RuntimeException{
    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
