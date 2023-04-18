package com.sukhobskaya.topjava.restaurant_rating_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "user_unique_email_index")})
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

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDateTime registered;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

}
