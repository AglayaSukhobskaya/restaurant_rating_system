package com.sukhobskaya.topjava.restaurant_rating_system.dto;

import javax.validation.constraints.NotBlank;

public record DishDto(
        @NotBlank(message = "Name should not be empty!")
        String name,

        Integer price) {
}
