package com.sukhobskaya.topjava.restaurant_rating_system.controller.user;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Dish;
import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import com.sukhobskaya.topjava.restaurant_rating_system.service.RestaurantService;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.RestaurantDto;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/profile/restaurants")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRestaurantController implements Handler {
    RestaurantService restaurantService;
    ModelMapper modelMapper;

    @GetMapping
    private List<RestaurantDto> getAll() {
        return restaurantService.getAll().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    public RestaurantDto getWithDayMenu(@PathVariable("id") int id) {
        Restaurant restaurant = restaurantService.get(id);
        List<Dish> dayMenu = restaurant.getMenu().stream().filter(food -> food.getDate().equals(LocalDate.now())).toList();
        restaurant.setMenu(dayMenu);
        return modelMapper.map(restaurant, RestaurantDto.class);
    }

}
