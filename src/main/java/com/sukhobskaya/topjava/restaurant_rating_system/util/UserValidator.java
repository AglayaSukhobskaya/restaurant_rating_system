package com.sukhobskaya.topjava.restaurant_rating_system.util;

import com.sukhobskaya.topjava.restaurant_rating_system.service.RegistrationService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final RegistrationService registrationService;

    @Autowired
    public UserValidator(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserTo userTo = (UserTo) target;
        if (registrationService.getByEmail(userTo.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "User with this email already exists!");
        }
    }

}
