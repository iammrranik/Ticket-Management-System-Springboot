package com.railway.ticket.management.system.domain;

public class FoodOrderDetails {
    private int id;
    private int orderId;
    private int foodItemId;
    private int quantity;
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
