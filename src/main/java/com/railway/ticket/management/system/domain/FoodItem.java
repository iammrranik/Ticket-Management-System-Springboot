package com.railway.ticket.management.system.domain;

public class FoodItem {
    private int id;
    private String itemName;
    private String category;
    private float price;
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
