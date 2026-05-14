package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.FoodOrderDetails;
import com.railway.ticket.management.system.service.implementation.FoodOrderDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food-order-detail")
public class FoodOrderDetailsApi {

    private final FoodOrderDetailsService foodOrderDetailsService;

    public FoodOrderDetailsApi(FoodOrderDetailsService foodOrderDetailsService) {
        this.foodOrderDetailsService = foodOrderDetailsService;
    }

    @PostMapping
    public void save(@RequestBody FoodOrderDetails foodOrderDetails) {
        this.foodOrderDetailsService.save(foodOrderDetails);
    }

    @PutMapping
    public void update(@RequestBody FoodOrderDetails foodOrderDetails) {
        this.foodOrderDetailsService.update(foodOrderDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.foodOrderDetailsService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<FoodOrderDetails> findById(@PathVariable int id) {
        return this.foodOrderDetailsService.findById(id);
    }

    @GetMapping("/{page}/{size}")
    public List<FoodOrderDetails> findAll(@PathVariable int page, @PathVariable int size) {
        return this.foodOrderDetailsService.findAll(page, size);
    }

    @GetMapping("/order/{orderId}")
    public List<FoodOrderDetails> findByOrderId(@PathVariable int orderId) {
        return this.foodOrderDetailsService.findByOrderId(orderId);
    }
}
