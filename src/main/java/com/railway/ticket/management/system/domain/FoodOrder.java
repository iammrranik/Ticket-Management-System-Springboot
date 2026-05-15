package com.railway.ticket.management.system.domain;

import com.railway.ticket.management.system.domain.enums.FoodOrderStatus;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class FoodOrder {
    private int id;
    @Positive
    private int ticketId;
    @Positive
    private float totalAmount;
    private LocalDateTime orderTimestamp;
    private FoodOrderStatus status;

    public FoodOrder(int id, int ticketId, float totalAmount, LocalDateTime orderTimestamp, FoodOrderStatus status) {
        setId(id);
        setTicketId(ticketId);
        setTotalAmount(totalAmount);
        setOrderTimestamp(orderTimestamp);
        setStatus(status);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }
    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }
    public LocalDateTime getOrderTimestamp() { return orderTimestamp; }
    public void setOrderTimestamp(LocalDateTime orderTimestamp) { this.orderTimestamp = orderTimestamp; }
    public FoodOrderStatus getStatus() { return status; }
    public void setStatus(FoodOrderStatus status) { this.status = status; }
}
