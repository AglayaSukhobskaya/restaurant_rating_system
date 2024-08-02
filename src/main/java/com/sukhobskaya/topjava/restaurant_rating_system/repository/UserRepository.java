package com.sukhobskaya.topjava.restaurant_rating_system.repository;

import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
