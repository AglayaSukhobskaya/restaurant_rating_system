package com.sukhobskaya.topjava.restaurant_rating_system.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank(message = "Name should not be empty!")
        String name,

        @NotBlank(message = "Email should not be empty!")
        @Email(message = "Email should be valid!")
        String email,

        @NotBlank(message = "Password should not be empty!")
        String password) {
}
