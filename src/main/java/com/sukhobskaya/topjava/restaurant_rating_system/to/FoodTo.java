package com.sukhobskaya.topjava.restaurant_rating_system.to;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodTo {

    @NotBlank(message = "Name should not be empty!")
    private String name;

    private Integer price;

}
