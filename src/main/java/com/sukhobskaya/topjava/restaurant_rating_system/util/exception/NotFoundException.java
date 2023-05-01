package com.sukhobskaya.topjava.restaurant_rating_system.util.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {}
    public NotFoundException(String msg) {
        super(msg);
    }
}
