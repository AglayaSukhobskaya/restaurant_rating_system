package com.sukhobskaya.topjava.restaurant_rating_system.controller.user;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Vote;
import com.sukhobskaya.topjava.restaurant_rating_system.security.PersonDetails;
import com.sukhobskaya.topjava.restaurant_rating_system.service.VoteService;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.VoteDto;
import com.sukhobskaya.topjava.restaurant_rating_system.util.validator.RestaurantValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.validator.VoteValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profile/votes")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserVoteController {
    VoteService voteService;
    RestaurantValidator restaurantValidator;
    ModelMapper modelMapper;
    VoteValidator voteValidator;

    @GetMapping
    public List<VoteDto> getAll(@AuthenticationPrincipal PersonDetails personDetails) {
        return voteService.getAll().stream()
//                .filter(vote -> vote.getUser().equals(personDetails.getUser()))
                .map(vote -> modelMapper.map(vote, VoteDto.class)).toList();
    }

    @GetMapping("/{id}")
    public VoteDto get(@AuthenticationPrincipal PersonDetails personDetails, @PathVariable("id") int id) {
        Vote vote = voteService.get(id);
        voteValidator.voteBelongsToUser(personDetails, vote);
        return modelMapper.map(vote, VoteDto.class);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@AuthenticationPrincipal PersonDetails personDetails,
                                             @RequestBody @Valid VoteDto voteDto) {

        restaurantValidator.isExist(voteDto.restaurantName());
        Vote vote = modelMapper.map(voteDto, Vote.class);
        vote.setUser(personDetails.getUser());

        voteService.create(vote);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@AuthenticationPrincipal PersonDetails personDetails,
                                             @PathVariable("id") int id,
                                             @RequestBody @Valid VoteDto voteDto,
                                             @RequestParam("restaurant_name") String restaurantName) {
        voteValidator.isExist(id);
        voteValidator.voteBelongsToUser(personDetails, voteService.get(id));

        voteService.update(id, restaurantName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@AuthenticationPrincipal PersonDetails personDetails,
                                             @PathVariable("id") int id) {
        voteValidator.isExist(id);
        voteValidator.voteBelongsToUser(personDetails, voteService.get(id));
        voteService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
