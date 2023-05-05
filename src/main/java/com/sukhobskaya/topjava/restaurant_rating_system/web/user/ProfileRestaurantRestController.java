package com.sukhobskaya.topjava.restaurant_rating_system.web.user;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Dish;
import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import com.sukhobskaya.topjava.restaurant_rating_system.service.RestaurantService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.RestaurantTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/profile/restaurants")
@AllArgsConstructor
public class ProfileRestaurantRestController implements Handler {

    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;

    @GetMapping
    private List<RestaurantTo> getAll() {
        return restaurantService.getAll().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantTo.class))
                .toList();
    }

    @GetMapping("/{id}")
    public RestaurantTo getWithDayMenu(@PathVariable("id") int id) {
        Restaurant restaurant = restaurantService.get(id);
        List<Dish> dayMenu = restaurant.getMenu().stream().filter(food -> food.getDate().equals(LocalDate.now())).toList();
        restaurant.setMenu(dayMenu);
        return modelMapper.map(restaurant, RestaurantTo.class);
    }

}
