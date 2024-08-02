package com.sukhobskaya.topjava.restaurant_rating_system.controller.admin;

import com.sukhobskaya.topjava.restaurant_rating_system.dto.UserDto;
import com.sukhobskaya.topjava.restaurant_rating_system.service.UserService;
import com.sukhobskaya.topjava.restaurant_rating_system.util.validator.UserValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.ValidationUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController implements Handler {
    UserService userService;
    UserValidator userValidator;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable("id") Integer id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Integer id,
                                             @RequestBody @Valid UserDto userDto,
                                             BindingResult bindingResult) {
        userValidator.isExist(id);
        userValidator.validate(userDto, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        userService.update(id, userDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        userValidator.isExist(id);
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
