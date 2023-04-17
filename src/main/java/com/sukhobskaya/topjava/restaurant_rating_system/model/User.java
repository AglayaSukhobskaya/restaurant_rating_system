package com.sukhobskaya.topjava.restaurant_rating_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractNamedEntity {

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email should not be empty!")
    @Email(message = "Email should be valid!")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password should not be empty!")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "registered")
    private LocalDateTime registered;

}
