package com.railway.ticket.management.system.domain;

import java.time.LocalDateTime;

public class FoodOrder {
    private int id;
    private int ticketId;
    private float totalAmount;
    private LocalDateTime orderTimestamp;
    private String status;

    public FoodOrder(int id, int ticketId, float totalAmount, LocalDateTime orderTimestamp, String status) {
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
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
