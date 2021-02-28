package com.thoughtworks.capacity.gtb.mvc;

import java.util.function.Supplier;

public class UserNotfoundException extends RuntimeException {
    public UserNotfoundException(String message) {
        super(message);
    }
}
