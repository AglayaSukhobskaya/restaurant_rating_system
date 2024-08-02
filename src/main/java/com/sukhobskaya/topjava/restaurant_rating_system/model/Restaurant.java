package com.sukhobskaya.topjava.restaurant_rating_system.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurant extends AbstractBaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Name should not be empty!")
    String name;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Dish> menu;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Vote> votes;
}
