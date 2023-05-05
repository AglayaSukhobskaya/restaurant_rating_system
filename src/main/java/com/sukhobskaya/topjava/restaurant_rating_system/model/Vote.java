package com.sukhobskaya.topjava.restaurant_rating_system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"user_id", "vote_date"}, name = "vote_unique_user_vote_date_index")})
@Getter
@Setter
@NoArgsConstructor
public class Vote extends AbstractBaseEntity {

    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;
}
