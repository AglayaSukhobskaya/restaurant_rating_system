package com.sukhobskaya.topjava.restaurant_rating_system.repository;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    Optional<Dish> findByName(String name);
}
