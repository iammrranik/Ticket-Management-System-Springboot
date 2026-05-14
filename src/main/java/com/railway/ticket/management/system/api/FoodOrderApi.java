package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.FoodOrder;
import com.railway.ticket.management.system.service.implementation.FoodOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food-order")
public class FoodOrderApi {

    private final FoodOrderService foodOrderService;

    public FoodOrderApi(FoodOrderService foodOrderService) {
        this.foodOrderService = foodOrderService;
    }

    @PostMapping
    public void save(@RequestBody FoodOrder foodOrder) {this.foodOrderService.save(foodOrder);}

    @PutMapping
    public void update(@RequestBody FoodOrder foodOrder) {this.foodOrderService.update(foodOrder);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.foodOrderService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<FoodOrder> findById(@PathVariable int id) {return this.foodOrderService.findById(id);}

    @GetMapping("/{page}/{size}")
    public List<FoodOrder> findAll(@PathVariable int page, @PathVariable int size) {
        return this.foodOrderService.findAll(page, size);
    }

    @GetMapping("/ticket/{ticketId}")
    public List<FoodOrder> findByTicketId(@PathVariable int ticketId) {
        return this.foodOrderService.findByTicketId(ticketId);
    }

    @PostMapping("/place")
    public FoodOrder placeOrder(@RequestBody FoodOrder foodOrder) {
        return this.foodOrderService.placeOrder(foodOrder);
    }

    @PutMapping("/status/{orderId}/{status}")
    public void updateStatus(@PathVariable int orderId, @PathVariable String status) {
        this.foodOrderService.updateOrderStatus(orderId, status);
    }
}
