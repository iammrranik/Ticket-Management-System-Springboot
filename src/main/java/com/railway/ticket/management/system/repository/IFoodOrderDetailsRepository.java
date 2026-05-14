package com.railway.ticket.management.system.repository;

import com.railway.ticket.management.system.domain.FoodOrderDetails;

import java.util.List;
import java.util.Optional;

public interface IFoodOrderDetailsRepository {
    int save(FoodOrderDetails foodOrderDetails);
    Optional<FoodOrderDetails> findById(int id);
    List<FoodOrderDetails> findByOrderId(int orderId);
    List<FoodOrderDetails> findAll(int page, int size);
    int count();
    int update(FoodOrderDetails foodOrderDetails);
    int deleteById(int id);
}
