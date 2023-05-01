package com.sukhobskaya.topjava.restaurant_rating_system.util;

import com.sukhobskaya.topjava.restaurant_rating_system.repository.RestaurantRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.to.RestaurantTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class RestaurantValidator implements Validator {

    private final RestaurantRepository restaurantRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return RestaurantTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull("target", "Restaurant should not be null!");
        RestaurantTo restaurantTo = (RestaurantTo) target;
        if (restaurantRepository.findByName(restaurantTo.getName()).isPresent()) {
            errors.rejectValue("name", "", "Restaurant with this name already exists!");
        }
    }

    public void isExist(int id) {
        if (restaurantRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Restaurant with id=" + id + " not found!");
        }
    }

    public void isExist(String name) {
        if (restaurantRepository.findByName(name).isEmpty()) {
            throw new NotFoundException("Restaurant with name " + name + " not found!");
        }
    }

}
