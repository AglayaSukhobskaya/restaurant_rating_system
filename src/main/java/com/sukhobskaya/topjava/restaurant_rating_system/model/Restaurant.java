package com.sukhobskaya.topjava.restaurant_rating_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private List<Food> menu;

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Restaurant(Integer id, String name, List<Food> menu) {
        super(id);
        this.name = name;
        this.menu = menu;
    }
}
