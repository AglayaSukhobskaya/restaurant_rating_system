package com.sukhobskaya.topjava.restaurant_rating_system.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantTo {

    @NotBlank(message = "Name should not be empty!")
    private String name;

    private List<DishTo> menu;

}
