package com.sukhobskaya.topjava.restaurant_rating_system.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private long time;
}
