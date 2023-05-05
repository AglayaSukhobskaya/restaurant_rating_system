package com.sukhobskaya.topjava.restaurant_rating_system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant extends AbstractBaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Name should not be empty!")
    private String name;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> menu;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Vote> votes;
}
