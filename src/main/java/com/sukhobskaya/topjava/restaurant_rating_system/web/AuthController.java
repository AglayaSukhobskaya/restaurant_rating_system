package com.sukhobskaya.topjava.restaurant_rating_system.web;

import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
import com.sukhobskaya.topjava.restaurant_rating_system.security.JWTUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.service.RegistrationService;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.UserDto;
import com.sukhobskaya.topjava.restaurant_rating_system.util.UserValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.ValidationUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController implements Handler {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;

    @PostMapping("/registration")
    public Map<String, String> register(@RequestBody @Valid UserDto userDto,
                        BindingResult bindingResult) {

        userValidator.validate(userDto, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        registrationService.register(modelMapper.map(userDto, User.class));

        String token = jwtUtil.generateToken(userDto.email());
        return Map.of("jwt-token", token);
    }
}
