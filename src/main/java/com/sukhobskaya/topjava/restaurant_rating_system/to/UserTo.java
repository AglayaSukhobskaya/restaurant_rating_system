package com.sukhobskaya.topjava.restaurant_rating_system.to;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
