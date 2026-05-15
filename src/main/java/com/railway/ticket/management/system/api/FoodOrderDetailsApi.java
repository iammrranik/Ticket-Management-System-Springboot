package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.FoodOrderDetails;
import com.railway.ticket.management.system.service.implementation.FoodOrderDetailsService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-order-detail")
public class FoodOrderDetailsApi {

    private final FoodOrderDetailsService foodOrderDetailsService;

    public FoodOrderDetailsApi(FoodOrderDetailsService foodOrderDetailsService) {
        this.foodOrderDetailsService = foodOrderDetailsService;
    }

    @PostMapping
    public void save(@Valid @RequestBody FoodOrderDetails foodOrderDetails) {
        this.foodOrderDetailsService.save(foodOrderDetails);
    }

    @PutMapping
    public void update(@Valid @RequestBody FoodOrderDetails foodOrderDetails) {
        this.foodOrderDetailsService.update(foodOrderDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        int result = this.foodOrderDetailsService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrderDetails> findById(@PathVariable int id) {
        return this.foodOrderDetailsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<FoodOrderDetails> findAll(@RequestParam int page, @RequestParam int size) {
        return this.foodOrderDetailsService.findAll(page, size);
    }

    @GetMapping("/order/{orderId}")
    public List<FoodOrderDetails> findByOrderId(@PathVariable int orderId) {
        return this.foodOrderDetailsService.findByOrderId(orderId);
    }
}
