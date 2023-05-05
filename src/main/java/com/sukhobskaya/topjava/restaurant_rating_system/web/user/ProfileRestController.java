package com.sukhobskaya.topjava.restaurant_rating_system.web.user;

import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
import com.sukhobskaya.topjava.restaurant_rating_system.security.PersonDetails;
import com.sukhobskaya.topjava.restaurant_rating_system.service.UserService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.UserTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.UserValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.ValidationUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileRestController implements Handler {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;

    @GetMapping
    public UserTo get(@AuthenticationPrincipal PersonDetails personDetails) {
        return modelMapper.map(personDetails.getUser(), UserTo.class);
    }

    // пока не работает
    @PutMapping
    public ResponseEntity<HttpStatus> update(@AuthenticationPrincipal PersonDetails personDetails,
                                             @RequestBody @Valid UserTo userTo,
                                             BindingResult bindingResult) {

        userValidator.validate(userTo, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        userService.update(personDetails.getUser().getId(), modelMapper.map(userTo, User.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // пока не работает (после удаления падает метод get())
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@AuthenticationPrincipal PersonDetails personDetails) {
        userService.delete(personDetails.getUser().getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
