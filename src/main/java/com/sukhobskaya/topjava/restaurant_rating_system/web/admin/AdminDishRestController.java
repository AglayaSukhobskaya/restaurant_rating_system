package com.sukhobskaya.topjava.restaurant_rating_system.web.admin;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Dish;
import com.sukhobskaya.topjava.restaurant_rating_system.service.DishService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.DishTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.DishValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.RestaurantValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.ValidationUtil;
import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.Handler;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/dishes")
@AllArgsConstructor
public class AdminDishRestController implements Handler {

    private final DishService dishService;
    private final ModelMapper modelMapper;
    private final DishValidator foodValidator;
    private final RestaurantValidator restaurantValidator;

    @GetMapping
    private List<DishTo> getAll() {
        return dishService.getAll().stream()
                .map(food -> modelMapper.map(food, DishTo.class))
                .toList();
    }

    @GetMapping("/{id}")
    public DishTo get(@PathVariable("id") int id) {
        return modelMapper.map(dishService.get(id), DishTo.class);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid DishTo foodTo,
                                             @RequestParam("restaurant_name") String restaurantName,
                                             BindingResult bindingResult) {

        foodValidator.validate(foodTo, bindingResult);
        restaurantValidator.isExist(restaurantName);
        ValidationUtil.checkDataValidity(bindingResult);

        dishService.create(modelMapper.map(foodTo, Dish.class), restaurantName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid DishTo foodTo,
                                             @RequestParam("restaurant_name") String restaurantName,
                                             BindingResult bindingResult) {

        foodValidator.isExist(id);
        foodValidator.validate(foodTo, bindingResult);
        restaurantValidator.isExist(restaurantName);
        // добавить проверку, принадлежит ли еда ресторану
        ValidationUtil.checkDataValidity(bindingResult);

        dishService.update(id, modelMapper.map(foodTo, Dish.class), restaurantName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        foodValidator.isExist(id);
        dishService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
