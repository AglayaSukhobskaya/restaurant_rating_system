package com.sukhobskaya.topjava.restaurant_rating_system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class VoteDto {

    private LocalDate voteDate;

    @NotBlank(message = "Name should not be empty!")
    private String restaurantName;

    public VoteDto(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
