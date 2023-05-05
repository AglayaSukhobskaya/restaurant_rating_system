package com.sukhobskaya.topjava.restaurant_rating_system.util;

import com.sukhobskaya.topjava.restaurant_rating_system.repository.DishRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.to.DishTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class DishValidator implements Validator {
    public final DishRepository dishRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return DishTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull("target", "Dish should not be null!");
        DishTo foodTo = (DishTo) target;
        if (dishRepository.findByName(foodTo.getName()).isPresent()) {
            errors.rejectValue("name", "", "Dish with this name already exists!");
        }
    }

    public void isExist(int id) {
        if (dishRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Dish with id=" + id + " not found!");
        }
    }

    public void isExist(String name) {
        if (dishRepository.findByName(name).isEmpty()) {
            throw new NotFoundException("Dish with name " + name + " not found!");
        }
    }

}
