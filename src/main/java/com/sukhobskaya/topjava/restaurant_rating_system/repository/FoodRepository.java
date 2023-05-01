package com.sukhobskaya.topjava.restaurant_rating_system.repository;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    Optional<Food> findByName(String name);
}
