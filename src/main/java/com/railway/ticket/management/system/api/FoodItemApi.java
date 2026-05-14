package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.FoodItem;
import com.railway.ticket.management.system.service.implementation.FoodItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food-item")
public class FoodItemApi {

    private final FoodItemService foodItemService;

    public FoodItemApi(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public void save(@RequestBody FoodItem foodItem) {this.foodItemService.save(foodItem);}

    @PutMapping
    public void update(@RequestBody FoodItem foodItem) {this.foodItemService.update(foodItem);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.foodItemService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<FoodItem> findById(@PathVariable int id) {return this.foodItemService.findById(id);}

    @GetMapping("/{page}/{size}")
    public List<FoodItem> findAll(@PathVariable int page, @PathVariable int size) {
        return this.foodItemService.findAll(page, size);
    }

    @GetMapping("/category/{category}")
    public List<FoodItem> findByCategory(@PathVariable String category) {
        return this.foodItemService.findByCategory(category);
    }

    @GetMapping("/check-availability/{foodItemId}/{quantity}")
    public boolean checkAvailability(@PathVariable int foodItemId, @PathVariable int quantity) {
        return this.foodItemService.isFoodAvailable(foodItemId, quantity);
    }

    @PutMapping("/quantity/{foodItemId}/{quantity}")
    public void updateQuantity(@PathVariable int foodItemId, @PathVariable int quantity) {
        this.foodItemService.updateAvailableQuantity(foodItemId, quantity);
    }
}
