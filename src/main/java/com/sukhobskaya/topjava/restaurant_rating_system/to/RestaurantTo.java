package com.sukhobskaya.topjava.restaurant_rating_system.to;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantTo {

    @NotBlank(message = "Name should not be empty!")
    private String name;

    private List<FoodTo> menu;

}
