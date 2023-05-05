package com.sukhobskaya.topjava.restaurant_rating_system.repository;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
