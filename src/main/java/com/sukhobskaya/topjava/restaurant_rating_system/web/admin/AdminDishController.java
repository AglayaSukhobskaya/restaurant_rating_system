package com.sukhobskaya.topjava.restaurant_rating_system.web.admin;

import com.sukhobskaya.topjava.restaurant_rating_system.dto.DishDto;
import com.sukhobskaya.topjava.restaurant_rating_system.service.DishService;
import com.sukhobskaya.topjava.restaurant_rating_system.util.DishValidator;
import com.sukhobskaya.topjava.restaurant_rating_system.util.RestaurantValidator;
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
@RequestMapping("/api/admin/dishes")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminDishController implements Handler {
    DishService dishService;
    DishValidator foodValidator;
    RestaurantValidator restaurantValidator;

    @GetMapping
    private List<DishDto> getAll() {
        return dishService.getAll();
    }

    @GetMapping("/{id}")
    public DishDto get(@PathVariable("id") Integer id) {
        return dishService.get(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid DishDto dishDto,
                                             @RequestParam("restaurant_name") String restaurantName,
                                             BindingResult bindingResult) {

        foodValidator.validate(dishDto, bindingResult);
        restaurantValidator.isExist(restaurantName);
        ValidationUtil.checkDataValidity(bindingResult);

        dishService.create(dishDto, restaurantName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Integer id, @RequestBody @Valid DishDto dishDto,
                                             @RequestParam("restaurant_name") String restaurantName,
                                             BindingResult bindingResult) {
        foodValidator.isExist(id);
        foodValidator.validate(dishDto, bindingResult);
        restaurantValidator.isExist(restaurantName);
        // добавить проверку, принадлежит ли еда ресторану
        ValidationUtil.checkDataValidity(bindingResult);

        dishService.update(id, dishDto, restaurantName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        foodValidator.isExist(id);
        dishService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
