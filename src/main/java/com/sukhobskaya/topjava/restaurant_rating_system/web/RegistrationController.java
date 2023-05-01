package com.sukhobskaya.topjava.restaurant_rating_system.web;

import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
import com.sukhobskaya.topjava.restaurant_rating_system.service.RegistrationService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.UserTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.UserValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.ValidationUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController implements Handler {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid UserTo userTo,
                                               BindingResult bindingResult) {

        userValidator.validate(userTo, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        registrationService.register(modelMapper.map(userTo, User.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
