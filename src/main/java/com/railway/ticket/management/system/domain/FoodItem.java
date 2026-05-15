package com.railway.ticket.management.system.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class FoodItem {
    private int id;
    @NotBlank
    private String itemName;
    @NotBlank
    private String category;
    @Positive
    private float price;
    @Positive
    private int availableQuantity;

    public FoodItem(int id, String itemName, String category, float price, int availableQuantity) {
        setId(id);
        setItemName(itemName);
        setCategory(category);
        setPrice(price);
        setAvailableQuantity(availableQuantity);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
}
