package com.sukhobskaya.topjava.restaurant_rating_system.web.user;

import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
import com.sukhobskaya.topjava.restaurant_rating_system.security.PersonDetails;
import com.sukhobskaya.topjava.restaurant_rating_system.service.PersonDetailsService;
import com.sukhobskaya.topjava.restaurant_rating_system.service.UserService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.UserTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.UserValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.ValidationUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileRestController implements Handler {

    private final UserService userService;
    private final PersonDetailsService personDetailsService;
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;

    @GetMapping
    public UserTo get(@AuthenticationPrincipal PersonDetails personDetails) {
        // тут пока ничего не работает
        return modelMapper.map(personDetailsService.loadUserByUsername(personDetails.getUsername()), UserTo.class);
    }

    // пока не работает
    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid UserTo userTo,
                                             BindingResult bindingResult) {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        userValidator.validate(userTo, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        userService.update(personDetails.getUser().getId(), modelMapper.map(userTo, User.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // пока не работает
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete() {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        userService.delete(personDetails.getUser().getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
