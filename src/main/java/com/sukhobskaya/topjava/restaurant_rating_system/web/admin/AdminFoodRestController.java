package com.sukhobskaya.topjava.restaurant_rating_system.web.admin;

import com.sukhobskaya.topjava.restaurant_rating_system.model.Food;
import com.sukhobskaya.topjava.restaurant_rating_system.service.FoodService;
import com.sukhobskaya.topjava.restaurant_rating_system.to.FoodTo;
import com.sukhobskaya.topjava.restaurant_rating_system.util.FoodValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.RestaurantValidator;
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
@RequestMapping("/api/admin/food")
@AllArgsConstructor
public class AdminFoodRestController implements Handler {

    private final FoodService foodService;
    private final ModelMapper modelMapper;
    private final FoodValidator foodValidator;
    private final RestaurantValidator restaurantValidator;

    @GetMapping
    private List<FoodTo> getAll() {
        return foodService.getAll().stream()
                .map(food -> modelMapper.map(food, FoodTo.class))
                .toList();
    }

    @GetMapping("/{id}")
    public FoodTo get(@PathVariable("id") int id) {
        return modelMapper.map(foodService.get(id), FoodTo.class);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid FoodTo foodTo,
                                             @RequestParam("restaurant_name") String restaurantName,
                                             BindingResult bindingResult) {

        foodValidator.validate(foodTo, bindingResult);
        restaurantValidator.isExist(restaurantName);
        ValidationUtil.checkDataValidity(bindingResult);

        foodService.create(modelMapper.map(foodTo, Food.class), restaurantName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid FoodTo foodTo,
                                             @RequestParam("restaurant_name") String restaurantName,
                                             BindingResult bindingResult) {

        foodValidator.isExist(id);
        foodValidator.validate(foodTo, bindingResult);
        restaurantValidator.isExist(restaurantName);
        // добавить проверку, принадлежит ли еда ресторану
        ValidationUtil.checkDataValidity(bindingResult);

        foodService.update(id, modelMapper.map(foodTo, Food.class), restaurantName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        foodValidator.isExist(id);
        foodService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
