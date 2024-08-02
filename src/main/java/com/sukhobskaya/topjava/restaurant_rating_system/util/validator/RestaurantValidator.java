package com.sukhobskaya.topjava.restaurant_rating_system.util.validator;

import com.sukhobskaya.topjava.restaurant_rating_system.repository.RestaurantRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.RestaurantDto;
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
public class RestaurantValidator implements Validator {
    RestaurantRepository restaurantRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return RestaurantDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull("target", "Restaurant should not be null!");
        RestaurantDto restaurantDto = (RestaurantDto) target;
        if (restaurantRepository.findByName(restaurantDto.name()).isPresent()) {
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
