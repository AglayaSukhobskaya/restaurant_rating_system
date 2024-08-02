package com.sukhobskaya.topjava.restaurant_rating_system.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public record RestaurantDto(
        @NotBlank(message = "Name should not be empty!")
        String name,

        List<DishDto> menu) {
}
