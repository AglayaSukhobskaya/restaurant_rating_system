package com.sukhobskaya.topjava.restaurant_rating_system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantDto {

    @NotBlank(message = "Name should not be empty!")
    private String name;

    private List<DishDto> menu;

}
