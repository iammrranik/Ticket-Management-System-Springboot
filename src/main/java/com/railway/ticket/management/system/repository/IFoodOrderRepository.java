package com.railway.ticket.management.system.repository;

import com.railway.ticket.management.system.domain.FoodOrder;
import com.railway.ticket.management.system.domain.enums.FoodOrderStatus;

import java.util.List;
import java.util.Optional;

public interface IFoodOrderRepository {
    int save(FoodOrder foodOrder);
    Optional<FoodOrder> findById(int id);
    List<FoodOrder> findByTicketId(int ticketId);
    List<FoodOrder> findAll(int page, int size);
    int count();
    int update(FoodOrder foodOrder);
    int deleteById(int id);
    int updateFoodOrderStatus(int orderId, FoodOrderStatus status);
}
