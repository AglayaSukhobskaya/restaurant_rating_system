package com.sukhobskaya.topjava.restaurant_rating_system.service;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Dish;
import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.RestaurantRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor

public class RestaurantService {
    RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant get(Integer id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant with id=" + id + " not found!"));
    }

    public Restaurant getByName(String name) {
        return restaurantRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Restaurant with name=\"" + name + "\" not found!"));
    }

    public void create(@NotNull Restaurant restaurant) {
        restaurant.setMenu(new ArrayList<>());
        restaurant.setVotes(new ArrayList<>());
        restaurantRepository.saveAndFlush(restaurant);
    }

    public void update(Integer id, @NotNull Restaurant restaurant) {
        restaurant.setId(id);
        restaurantRepository.saveAndFlush(restaurant);
    }

    public void delete(Integer id) {
        restaurantRepository.deleteById(id);
    }

    public List<Dish> getDayMenu(Integer id) {
        return restaurantRepository.findById(id)
                .map(value -> value.getMenu().stream()
                        .filter(dish -> Objects.equals(dish.getDate(), LocalDate.now()))
                        .toList())
                .orElseGet(ArrayList::new);
    }
}
