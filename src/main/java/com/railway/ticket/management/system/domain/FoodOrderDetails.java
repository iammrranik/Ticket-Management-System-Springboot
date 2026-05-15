package com.railway.ticket.management.system.domain;

import jakarta.validation.constraints.Positive;

public class FoodOrderDetails {
    private int id;
    @Positive
    private int orderId;
    @Positive
    private int foodItemId;
    @Positive
    private int quantity;
    @Positive
    private float subTotal;

    public FoodOrderDetails(int id, int orderId, int foodItemId, int quantity, float subTotal) {
        setId(id);
        setOrderId(orderId);
        setFoodItemId(foodItemId);
        setQuantity(quantity);
        setSubTotal(subTotal);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getFoodItemId() { return foodItemId; }
    public void setFoodItemId(int foodItemId) { this.foodItemId = foodItemId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public float getSubTotal() { return subTotal; }
    public void setSubTotal(float subTotal) { this.subTotal = subTotal; }
}
