package com.sukhobskaya.topjava.restaurant_rating_system.controller.user;

import com.sukhobskaya.topjava.restaurant_rating_system.model.User;
import com.sukhobskaya.topjava.restaurant_rating_system.service.UserService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.UserTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.UserErrorResponse;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/users")
public class AdminRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UserTo> getAll() {
        return userService.getAll().stream().map(this::convertToUserTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserTo get(@PathVariable("id") int id) {
        return convertToUserTo(userService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }







    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id was not found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private UserTo convertToUserTo(User user) {
        return modelMapper.map(user, UserTo.class);
    }

}
