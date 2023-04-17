package com.sukhobskaya.topjava.restaurant_rating_system.web.user;

import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileRestController {

    @PermitAll
    @GetMapping
    public String test() {
        return "test";
    }
}
