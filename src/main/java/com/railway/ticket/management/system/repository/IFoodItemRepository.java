package com.railway.ticket.management.system.repository;

import com.railway.ticket.management.system.domain.FoodItem;

import java.util.List;
import java.util.Optional;

public interface IFoodItemRepository {
    int save(FoodItem foodItem);
    Optional<FoodItem> findById(int id);
    List<FoodItem> findByCategory(String category);
    List<FoodItem> findAll(int page, int size);
    int count();
    int update(FoodItem foodItem);
    int deleteById(int id);
    int updateFoodItemAvailableQuantity(int foodItemId, int quantity);
    boolean isFoodAvailable(int foodItemId, int requestedQuantity);
}
