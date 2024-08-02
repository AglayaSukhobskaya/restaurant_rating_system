package com.sukhobskaya.topjava.restaurant_rating_system.util.validator;

import com.sukhobskaya.topjava.restaurant_rating_system.repository.UserRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.UserDto;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotValidDataException;
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
public class UserValidator implements Validator {
    UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull("target", "User should not be null!");
        UserDto userDto = (UserDto) target;
        if (userRepository.findByEmail(userDto.email()).isPresent()) {
            errors.rejectValue("email", "", "User with this email already exists!");
        }
    }

    public void isExist(int id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NotValidDataException("User with id=" + id + " not found!");
        }
    }

}
