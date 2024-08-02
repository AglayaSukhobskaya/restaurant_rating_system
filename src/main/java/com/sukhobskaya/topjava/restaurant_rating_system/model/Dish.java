package com.sukhobskaya.topjava.restaurant_rating_system.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dish")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dish extends AbstractBaseEntity {

    @NotBlank(message = "Name should not be empty!")
    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "price", nullable = false)
    Integer price;

    @NotNull
    @Column(name = "date", nullable = false)
    LocalDate date;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    Restaurant restaurant;
}
