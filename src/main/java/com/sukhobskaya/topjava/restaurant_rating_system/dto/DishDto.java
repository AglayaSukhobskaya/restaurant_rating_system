package com.sukhobskaya.topjava.restaurant_rating_system.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DishDto {

    @NotBlank(message = "Name should not be empty!")
    private String name;

    private Integer price;

}
