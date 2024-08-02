package com.sukhobskaya.topjava.restaurant_rating_system.service;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Dish;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.DishRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.DishDto;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DishService {
    DishRepository dishRepository;
    RestaurantService restaurantService;
    ModelMapper modelMapper;

    public List<DishDto> getAll() {
        return dishRepository.findAll().stream()
                .map(dish -> modelMapper.map(dish, DishDto.class))
                .toList();
    }

    public DishDto get(Integer id) {
        return dishRepository.findById(id)
                .map(dish -> modelMapper.map(dish, DishDto.class))
                .orElseThrow(() -> new NotFoundException("Dish with id=" + id + " not found!"));
    }

    public void create(@NotNull DishDto dishDto, String restaurantName) {
        var restaurant = restaurantService.getByName(restaurantName);
        var dish = modelMapper.map(dishDto, Dish.class);
        dish.setRestaurant(restaurant);
        dish.setDate(LocalDate.now());
        restaurant.getMenu().add(dish);
        dishRepository.saveAndFlush(dish);
    }

    public void update(Integer id, @NotNull DishDto dishDto, String restaurantName) {
        var dishToUpdate = modelMapper.map(get(id), Dish.class);
        var restaurant = restaurantService.getByName(restaurantName);
        dishToUpdate.setRestaurant(restaurant);
        dishToUpdate.setName(dishDto.name());
        dishToUpdate.setPrice(dishDto.price());
        restaurant.getMenu().add(dishToUpdate);
        dishRepository.saveAndFlush(dishToUpdate);
    }

    public void delete(Integer id) {
        dishRepository.deleteById(id);
    }
}
