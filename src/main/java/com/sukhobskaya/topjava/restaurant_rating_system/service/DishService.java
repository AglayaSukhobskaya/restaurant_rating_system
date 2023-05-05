package com.sukhobskaya.topjava.restaurant_rating_system.service;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Dish;
import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.DishRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.RestaurantRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish get(int id) {
        Optional<Dish> foundFood = dishRepository.findById(id);
        return foundFood.orElseThrow(() -> new NotFoundException("Dish with id=" + id + " not found!"));
    }

    @Transactional
    public void create(Dish food, String restaurantName) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findByName(restaurantName);
        food.setRestaurant(foundRestaurant.get());
        food.setDate(LocalDate.now());
        foundRestaurant.get().getMenu().add(food);
        dishRepository.save(food);
    }

    @Transactional
    public void update(int id, Dish food, String restaurantName) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findByName(restaurantName);
        food.setDate(dishRepository.findById(id).get().getDate());
        food.setRestaurant(foundRestaurant.get());
        foundRestaurant.get().getMenu().add(food);
        food.setId(id);
        dishRepository.save(food);
    }

    @Transactional
    public void delete(int id) {
        dishRepository.deleteById(id);
    }

}
