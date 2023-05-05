package com.sukhobskaya.topjava.restaurant_rating_system.service;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import com.sukhobskaya.topjava.restaurant_rating_system.model.Vote;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.RestaurantRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.VoteRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    public Vote get(int id) {
        Optional<Vote> foundVote = voteRepository.findById(id);
        return foundVote.orElseThrow(() -> new NotFoundException("Vote with id="
                + id + " not found!"));
    }

    @Transactional
    public void create(Vote vote) {
        vote.setVoteDate(LocalDate.now());
        Optional<Restaurant> restaurant = restaurantRepository.findByName(vote.getRestaurant().getName());
        vote.setRestaurant(restaurant.get());
        restaurant.get().getVotes().add(vote);

        voteRepository.save(vote);
    }

    @Transactional
    public void update(int id, String restaurantName) {
        Vote vote = voteRepository.findById(id).get();
        Restaurant restaurant = restaurantRepository.findByName(restaurantName).get();
        vote.setRestaurant(restaurant);
        restaurant.getVotes().add(vote);

        voteRepository.save(vote);
    }

    @Transactional
    public void delete(int id) {
        voteRepository.deleteById(id);
    }
}
