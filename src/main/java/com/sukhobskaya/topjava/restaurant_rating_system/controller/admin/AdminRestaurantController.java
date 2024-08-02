package com.sukhobskaya.topjava.restaurant_rating_system.controller.admin;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Restaurant;
import com.sukhobskaya.topjava.restaurant_rating_system.service.RestaurantService;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.DishDto;
import com.sukhobskaya.topjava.restaurant_rating_system.dto.RestaurantDto;
import com.sukhobskaya.topjava.restaurant_rating_system.util.validator.RestaurantValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.ValidationUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/restaurants")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminRestaurantController implements Handler {
    RestaurantService restaurantService;
    ModelMapper modelMapper;
    RestaurantValidator restaurantValidator;

    @GetMapping
    private List<RestaurantDto> getAll() {
        return restaurantService.getAll().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    public RestaurantDto get(@PathVariable("id") int id) {
        return modelMapper.map(restaurantService.get(id), RestaurantDto.class);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid RestaurantDto restaurantDto,
                                             BindingResult bindingResult) {

        restaurantValidator.validate(restaurantDto, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        restaurantService.create(modelMapper.map(restaurantDto, Restaurant.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid RestaurantDto restaurantDto,
                                             BindingResult bindingResult) {
        restaurantValidator.isExist(id);
        restaurantValidator.validate(restaurantDto, bindingResult);
        ValidationUtil.checkDataValidity(bindingResult);

        restaurantService.update(id, modelMapper.map(restaurantDto, Restaurant.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        restaurantValidator.isExist(id);
        restaurantService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/day_menu")
    public List<DishDto> getDayMenu(@PathVariable("id") int id) {
        return restaurantService.getDayMenu(id).stream()
                .map(food -> modelMapper.map(food, DishDto.class)).toList();
    }
}
