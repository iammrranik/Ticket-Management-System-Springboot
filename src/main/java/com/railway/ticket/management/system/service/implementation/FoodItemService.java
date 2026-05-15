package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.FoodItem;
import com.railway.ticket.management.system.repository.implementation.FoodItemRepository;
import com.railway.ticket.management.system.service.IFoodItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService implements IFoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    @Transactional
    public FoodItem save(FoodItem foodItem) {
        foodItemRepository.save(foodItem);
        return foodItem;
    }

    @Override
    public Optional<FoodItem> findById(int id) {
        return foodItemRepository.findById(id);
    }

    @Override
    public List<FoodItem> findByCategory(String category) {
        return foodItemRepository.findByCategory(category);
    }

    @Override
    public List<FoodItem> findAll(int page, int size) {
        return foodItemRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return foodItemRepository.count();
    }

    @Override
    @Transactional
    public int update(FoodItem foodItem) {
        return foodItemRepository.update(foodItem);
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        return foodItemRepository.deleteById(id);
    }

    @Override
    public boolean isFoodAvailable(int foodItemId, int requestedQuantity) {
        return foodItemRepository.isFoodAvailable(foodItemId, requestedQuantity);
    }

    @Override
    @Transactional
    public int updateAvailableQuantity(int foodItemId, int quantity) {
        return foodItemRepository.updateFoodItemAvailableQuantity(foodItemId, quantity);
    }
}
