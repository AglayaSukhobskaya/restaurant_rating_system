package com.sukhobskaya.topjava.restaurant_rating_system.util.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {}
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
