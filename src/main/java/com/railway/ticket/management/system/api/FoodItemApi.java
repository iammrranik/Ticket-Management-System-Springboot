package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.FoodItem;
import com.railway.ticket.management.system.service.implementation.FoodItemService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-item")
public class FoodItemApi {

    private final FoodItemService foodItemService;

    public FoodItemApi(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public void save(@Valid @RequestBody FoodItem foodItem) {
        System.out.println("POST /api/food-item - " + foodItem.getItemName());
        this.foodItemService.save(foodItem);
    }

    @PutMapping
    public void update(@Valid @RequestBody FoodItem foodItem) {
        System.out.println("PUT /api/food-item - id=" + foodItem.getId());
        this.foodItemService.update(foodItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        System.out.println("DELETE /api/food-item/" + id);
        int result = this.foodItemService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> findById(@PathVariable int id) {
        System.out.println("GET /api/food-item/" + id);
        return this.foodItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<FoodItem> findAll(@RequestParam int page, @RequestParam int size) {
        System.out.println("GET /api/food-item?page=" + page + "&size=" + size);
        return this.foodItemService.findAll(page, size);
    }

    @GetMapping("/category/{category}")
    public List<FoodItem> findByCategory(@PathVariable String category) {
        System.out.println("GET /api/food-item/category/" + category);
        return this.foodItemService.findByCategory(category);
    }

    @GetMapping("/check-availability/{foodItemId}/{quantity}")
    public boolean checkAvailability(@PathVariable int foodItemId, @PathVariable int quantity) {
        System.out.println("GET /api/food-item/check-availability/" + foodItemId + "/" + quantity);
        return this.foodItemService.isFoodAvailable(foodItemId, quantity);
    }

    @PutMapping("/quantity/{foodItemId}/{quantity}")
    public void updateQuantity(@PathVariable int foodItemId, @PathVariable int quantity) {
        System.out.println("PUT /api/food-item/quantity/" + foodItemId + "/" + quantity);
        this.foodItemService.updateAvailableQuantity(foodItemId, quantity);
    }
}
