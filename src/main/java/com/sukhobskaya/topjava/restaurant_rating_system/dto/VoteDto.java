package com.sukhobskaya.topjava.restaurant_rating_system.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public record VoteDto(
        LocalDate voteDate,

        @NotBlank(message = "Name should not be empty!")
        String restaurantName) {
}
