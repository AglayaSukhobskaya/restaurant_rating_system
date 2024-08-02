package com.sukhobskaya.topjava.restaurant_rating_system.controller.user;

import com.sukhobskaya.topjava.restaurant_rating_system.dto.UserDto;
import com.sukhobskaya.topjava.restaurant_rating_system.security.PersonDetails;
import com.sukhobskaya.topjava.restaurant_rating_system.service.UserService;
import com.sukhobskaya.topjava.restaurant_rating_system.util.validator.UserValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.ValidationUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController implements Handler {
    UserService userService;
    ModelMapper modelMapper;
    UserValidator userValidator;

    @GetMapping
    public UserDto get(@AuthenticationPrincipal PersonDetails personDetails) {
        return modelMapper.map(personDetails.getUser(), UserDto.class);
    }

    // пока не работает
    @PutMapping
    public ResponseEntity<HttpStatus> update(@AuthenticationPrincipal PersonDetails personDetails,
                                             @RequestBody @Valid UserDto userDto,
                                             BindingResult bindingResult) {

        userValidator.validate(userDto, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        userService.update(personDetails.getUser().getId(), userDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // пока не работает (после удаления падает метод get())
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@AuthenticationPrincipal PersonDetails personDetails) {
        userService.delete(personDetails.getUser().getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
