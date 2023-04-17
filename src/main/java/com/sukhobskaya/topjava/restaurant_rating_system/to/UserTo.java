package com.sukhobskaya.topjava.restaurant_rating_system.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTo {

    @NotBlank(message = "Name should not be empty!")
    private String name;

    @NotBlank(message = "Email should not be empty!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotBlank(message = "Password should not be empty!")
    private String password;
}
