package com.sukhobskaya.topjava.restaurant_rating_system.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"user_id", "vote_date"}, name = "vote_unique_user_vote_date_index")})
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vote extends AbstractBaseEntity {

    @Column(name = "vote_date", nullable = false)
    LocalDate voteDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    Restaurant restaurant;
}
