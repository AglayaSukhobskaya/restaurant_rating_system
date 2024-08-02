package com.sukhobskaya.topjava.restaurant_rating_system.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "user_unique_email_index")})
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractNamedEntity implements Serializable {

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email should not be empty!")
    @Email(message = "Email should be valid!")
    String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password should not be empty!")
    String password;

    // выяснить, нахрена это нужно
    @Column(name = "enabled", nullable = false)
    boolean enabled;

    @NotNull
    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    LocalDateTime registered;


    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 20)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<Role> roles;
}
