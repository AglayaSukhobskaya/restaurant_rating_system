package com.sukhobskaya.topjava.restaurant_rating_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name should not be empty!")
    private String name;

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
