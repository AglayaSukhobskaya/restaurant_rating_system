package com.sukhobskaya.topjava.restaurant_rating_system.util.validator;

import com.sukhobskaya.topjava.restaurant_rating_system.repository.DishRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.DishDto;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DishValidator implements Validator {
    DishRepository dishRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return DishDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull("target", "Dish should not be null!");
        DishDto foodTo = (DishDto) target;
        if (dishRepository.findByName(foodTo.name()).isPresent()) {
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
