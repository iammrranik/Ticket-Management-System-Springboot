package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.FoodOrder;
import com.railway.ticket.management.system.domain.enums.FoodOrderStatus;
import com.railway.ticket.management.system.service.implementation.FoodOrderService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-order")
public class FoodOrderApi {

    private final FoodOrderService foodOrderService;

    public FoodOrderApi(FoodOrderService foodOrderService) {
        this.foodOrderService = foodOrderService;
    }

    @PostMapping
    public void save(@Valid @RequestBody FoodOrder foodOrder) {
        System.out.println("POST /api/food-order - ticketId=" + foodOrder.getTicketId());
        this.foodOrderService.save(foodOrder);
    }

    @PutMapping
    public void update(@Valid @RequestBody FoodOrder foodOrder) {
        System.out.println("PUT /api/food-order - id=" + foodOrder.getId());
        this.foodOrderService.update(foodOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        System.out.println("DELETE /api/food-order/" + id);
        int result = this.foodOrderService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> findById(@PathVariable int id) {
        System.out.println("GET /api/food-order/" + id);
        return this.foodOrderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<FoodOrder> findAll(@RequestParam int page, @RequestParam int size) {
        System.out.println("GET /api/food-order?page=" + page + "&size=" + size);
        return this.foodOrderService.findAll(page, size);
    }

    @GetMapping("/ticket/{ticketId}")
    public List<FoodOrder> findByTicketId(@PathVariable int ticketId) {
        System.out.println("GET /api/food-order/ticket/" + ticketId);
        return this.foodOrderService.findByTicketId(ticketId);
    }

    @PostMapping("/place")
    public FoodOrder placeOrder(@Valid @RequestBody FoodOrder foodOrder) {
        System.out.println("POST /api/food-order/place - ticketId=" + foodOrder.getTicketId());
        return this.foodOrderService.placeOrder(foodOrder);
    }

    @PutMapping("/status/{orderId}/{status}")
    public void updateStatus(@PathVariable int orderId, @PathVariable FoodOrderStatus status) {
        System.out.println("PUT /api/food-order/status/" + orderId + "/" + status);
        this.foodOrderService.updateOrderStatus(orderId, status);
    }
}
