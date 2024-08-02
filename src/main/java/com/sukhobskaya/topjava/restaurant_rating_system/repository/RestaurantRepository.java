package com.sukhobskaya.topjava.restaurant_rating_system.repository;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findByName(String restaurantName);
}
