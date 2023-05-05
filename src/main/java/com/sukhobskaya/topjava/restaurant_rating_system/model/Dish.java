package com.sukhobskaya.topjava.restaurant_rating_system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
public class Dish extends AbstractBaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Name should not be empty!")
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;
}
