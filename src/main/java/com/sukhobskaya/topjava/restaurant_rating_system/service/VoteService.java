package com.sukhobskaya.topjava.restaurant_rating_system.service;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Vote;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.VoteRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VoteService {
    VoteRepository voteRepository;
    RestaurantService restaurantService;

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    public Vote get(Integer id) {
        return voteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vote with id=" + id + " not found!"));
    }

    public void create(@NotNull Vote vote) {
        var restaurant = restaurantService.getByName(vote.getRestaurant().getName());
        vote.setRestaurant(restaurant);
        vote.setVoteDate(LocalDate.now());
        restaurant.getVotes().add(vote);
        voteRepository.saveAndFlush(vote);
    }

    public void update(Integer id, String restaurantName) {
        var vote = get(id);
        var restaurant = restaurantService.getByName(restaurantName);
        vote.setRestaurant(restaurant);
        restaurant.getVotes().add(vote);
        voteRepository.saveAndFlush(vote);
    }

    public void delete(Integer id) {
        voteRepository.deleteById(id);
    }
}
