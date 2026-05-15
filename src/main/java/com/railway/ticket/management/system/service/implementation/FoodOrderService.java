package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.FoodItem;
import com.railway.ticket.management.system.domain.FoodOrder;
import com.railway.ticket.management.system.domain.FoodOrderDetails;
import com.railway.ticket.management.system.domain.enums.FoodOrderStatus;
import com.railway.ticket.management.system.repository.implementation.FoodItemRepository;
import com.railway.ticket.management.system.repository.implementation.FoodOrderDetailsRepository;
import com.railway.ticket.management.system.repository.implementation.FoodOrderRepository;
import com.railway.ticket.management.system.service.IFoodOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService implements IFoodOrderService {

    private final FoodOrderRepository foodOrderRepository;
    private final FoodItemRepository foodItemRepository;
    private final FoodOrderDetailsRepository foodOrderDetailsRepository;

    public FoodOrderService(FoodOrderRepository foodOrderRepository,
                            FoodItemRepository foodItemRepository,
                            FoodOrderDetailsRepository foodOrderDetailsRepository) {
        this.foodOrderRepository = foodOrderRepository;
        this.foodItemRepository = foodItemRepository;
        this.foodOrderDetailsRepository = foodOrderDetailsRepository;
    }

    @Override
    @Transactional
    public FoodOrder save(FoodOrder foodOrder) {
        foodOrderRepository.save(foodOrder);
        return foodOrder;
    }

    @Override
    public Optional<FoodOrder> findById(int id) {
        return foodOrderRepository.findById(id);
    }

    @Override
    public List<FoodOrder> findByTicketId(int ticketId) {
        return foodOrderRepository.findByTicketId(ticketId);
    }

    @Override
    public List<FoodOrder> findAll(int page, int size) {
        return foodOrderRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return foodOrderRepository.count();
    }

    @Override
    @Transactional
    public int update(FoodOrder foodOrder) {
        return foodOrderRepository.update(foodOrder);
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        return foodOrderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public FoodOrder placeOrder(FoodOrder foodOrder) {
        if (foodOrder.getId() == 0) {
            foodOrderRepository.save(foodOrder);
        }

        List<FoodOrderDetails> detailsList = foodOrderDetailsRepository.findByOrderId(foodOrder.getId());

        for (FoodOrderDetails details : detailsList) {
            Optional<FoodItem> foodItemOpt = foodItemRepository.findById(details.getFoodItemId());
            if (foodItemOpt.isEmpty() || foodItemOpt.get().getAvailableQuantity() < details.getQuantity()) {
                foodOrder.setStatus(FoodOrderStatus.FAILED);
                foodOrder.setOrderTimestamp(LocalDateTime.now());
                foodOrderRepository.save(foodOrder);
                return foodOrder;
            }
        }

        for (FoodOrderDetails details : detailsList) {
            int updated = foodItemRepository.deductFoodItemQuantity(details.getFoodItemId(), details.getQuantity());
            if (updated == 0) {
                throw new RuntimeException("Stock insufficient for Item ID: " + details.getFoodItemId());
            }
        }

        foodOrder.setStatus(FoodOrderStatus.PLACED);
        foodOrder.setOrderTimestamp(LocalDateTime.now());
        foodOrderRepository.save(foodOrder);
        return foodOrder;
    }


    @Override
    public int updateOrderStatus(int orderId, FoodOrderStatus status) {
        return foodOrderRepository.updateFoodOrderStatus(orderId, status);
    }
}

