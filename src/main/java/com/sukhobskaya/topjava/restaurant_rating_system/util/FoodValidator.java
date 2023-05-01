package com.sukhobskaya.topjava.restaurant_rating_system.util;

import com.sukhobskaya.topjava.restaurant_rating_system.repository.FoodRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.to.FoodTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class FoodValidator implements Validator {
    public final FoodRepository foodRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return FoodTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull("target", "Food should not be null!");
        FoodTo foodTo = (FoodTo) target;
        if (foodRepository.findByName(foodTo.getName()).isPresent()) {
            errors.rejectValue("name", "", "Food with this name already exists!");
        }
    }

    public void isExist(int id) {
        if (foodRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Food with id=" + id + " not found!");
        }
    }

    public void isExist(String name) {
        if (foodRepository.findByName(name).isEmpty()) {
            throw new NotFoundException("Food with name " + name + " not found!");
        }
    }

}
