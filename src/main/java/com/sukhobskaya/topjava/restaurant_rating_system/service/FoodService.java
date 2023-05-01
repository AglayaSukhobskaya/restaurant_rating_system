package com.sukhobskaya.topjava.restaurant_rating_system.service;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Food;
import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.FoodRepository;
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
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    public Food get(int id) {
        Optional<Food> foundFood = foodRepository.findById(id);
        return foundFood.orElseThrow(() -> new NotFoundException("Food with id=" + id + " not found!"));
    }

    @Transactional
    public void create(Food food, String restaurantName) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findByName(restaurantName);
        food.setRestaurant(foundRestaurant.get());
        food.setDate(LocalDate.now());
        foundRestaurant.get().getMenu().add(food);
        foodRepository.save(food);
    }

    @Transactional
    public void update(int id, Food food, String restaurantName) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findByName(restaurantName);
        food.setDate(foodRepository.findById(id).get().getDate());
        food.setRestaurant(foundRestaurant.get());
        foundRestaurant.get().getMenu().add(food);
        food.setId(id);
        foodRepository.save(food);
    }

    @Transactional
    public void delete(int id) {
        foodRepository.deleteById(id);
    }

}
