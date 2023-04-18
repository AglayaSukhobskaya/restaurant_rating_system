package com.sukhobskaya.topjava.restaurant_rating_system.config;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Role;
import com.sukhobskaya.topjava.restaurant_rating_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.getAuthority())
                .requestMatchers("/registration", "/login").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().hasAnyRole(Role.USER.getAuthority(), Role.ADMIN.getAuthority());

//                .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name())
//                .requestMatchers("/api/**").authenticated();
        return http.build();
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
