package com.sukhobskaya.topjava.restaurant_rating_system.controller;

import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
import com.sukhobskaya.topjava.restaurant_rating_system.service.RegistrationService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.UserTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.UserErrorResponse;
import com.sukhobskaya.topjava.restaurant_rating_system.util.UserValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.UserNotRegisteredException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;

    @Autowired
    public RegistrationController(RegistrationService registrationService, ModelMapper modelMapper, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
        this.userValidator = userValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid UserTo userTo,
                                                   BindingResult bindingResult) {

        userValidator.validate(userTo, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new UserNotRegisteredException(errorMsg.toString());
        }

        registrationService.register(convertToUser(userTo));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotRegisteredException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private User convertToUser(UserTo userTo) {
        return modelMapper.map(userTo, User.class);
    }
}
