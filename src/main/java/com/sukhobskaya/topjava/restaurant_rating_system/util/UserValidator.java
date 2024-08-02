package com.sukhobskaya.topjava.restaurant_rating_system.util;

import com.sukhobskaya.topjava.restaurant_rating_system.repository.UserRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.UserDto;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotValidDataException;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull("target", "User should not be null!");
        UserDto userDto = (UserDto) target;
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "User with this email already exists!");
        }
    }

    public void isExist(int id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NotValidDataException("User with id=" + id + " not found!");
        }
    }

}
