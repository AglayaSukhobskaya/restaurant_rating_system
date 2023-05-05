package com.sukhobskaya.topjava.restaurant_rating_system.util;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Vote;
import com.sukhobskaya.topjava.restaurant_rating_system.repository.VoteRepository;
import com.sukhobskaya.topjava.restaurant_rating_system.security.PersonDetails;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotFoundException;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotValidDataException;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class VoteValidator implements Validator {

    private final VoteRepository voteRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Vote.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull("target", "Vote should not be null!");
        Vote vote = (Vote) target;
        if (voteRepository.findById(vote.getId()).isPresent()) {
            errors.rejectValue("id", "", "Vote with this id already exists!");
        }
    }

    public void isExist(int id) {
        if (voteRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Vote with id=" + id + " not found!");
        }
    }

    public void voteBelongsToUser(PersonDetails personDetails, Vote vote) {
        if (!vote.getUser().getId().equals(personDetails.getUser().getId())) {
            throw new NotValidDataException("Vote with id=" + vote.getId() + " does not belong to this user!");
        }
    }

}
