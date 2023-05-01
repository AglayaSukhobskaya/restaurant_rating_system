package com.sukhobskaya.topjava.restaurant_rating_system.web.admin;

import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@AllArgsConstructor
public class AdminRestController implements Handler {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;

    @GetMapping
    public List<UserTo> getAll() {
        return userService.getAll().stream()
                .map(user -> modelMapper.map(user, UserTo.class))
                .toList();
    }

    @GetMapping("/{id}")
    public UserTo get(@PathVariable("id") int id) {
        return modelMapper.map(userService.get(id), UserTo.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid UserTo userTo,
                                             BindingResult bindingResult) {
        userValidator.isExist(id);
        userValidator.validate(userTo, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        userService.update(id, modelMapper.map(userTo, User.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        userValidator.isExist(id);
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
