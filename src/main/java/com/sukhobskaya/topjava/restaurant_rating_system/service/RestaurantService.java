package com.sukhobskaya.topjava.restaurant_rating_system.service;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Dish;
import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.RestaurantRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant get(int id) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(id);
        return foundRestaurant.orElseThrow(() -> new NotFoundException("Restaurant with id="
                + id + " not found!"));
    }

    @Transactional
    public void create(Restaurant restaurant) {
        restaurant.setMenu(new ArrayList<>());
        restaurant.setVotes(new ArrayList<>());
        restaurantRepository.save(restaurant);
    }

    @Transactional
    public void update(int id, Restaurant restaurant) {
        restaurant.setId(id);
        restaurantRepository.save(restaurant);
    }

    @Transactional
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    public List<Dish> getDayMenu(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.map(value -> value.getMenu().stream()
                .filter(food -> food.getDate().equals(LocalDate.now())).toList()).orElseGet(ArrayList::new);
    }

}
