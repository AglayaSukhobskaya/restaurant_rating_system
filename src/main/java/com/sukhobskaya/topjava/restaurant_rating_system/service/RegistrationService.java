package com.sukhobskaya.topjava.restaurant_rating_system.service;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Role;
import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public void register(@NotNull User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistered(LocalDateTime.now());
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.saveAndFlush(user);
    }
}
