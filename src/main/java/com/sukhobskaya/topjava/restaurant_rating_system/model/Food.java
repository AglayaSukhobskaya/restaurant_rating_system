package com.sukhobskaya.topjava.restaurant_rating_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "food")
@Getter
@Setter
@NoArgsConstructor
public class Food extends AbstractBaseEntity {

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
