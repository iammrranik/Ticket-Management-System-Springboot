package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.FoodOrderDetails;
import com.railway.ticket.management.system.repository.implementation.FoodOrderDetailsRepository;
import com.railway.ticket.management.system.service.IFoodOrderDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderDetailsService implements IFoodOrderDetailsService {

    private final FoodOrderDetailsRepository foodOrderDetailsRepository;

    public FoodOrderDetailsService(FoodOrderDetailsRepository foodOrderDetailsRepository) {
        this.foodOrderDetailsRepository = foodOrderDetailsRepository;
    }

    @Override
    @Transactional
    public FoodOrderDetails save(FoodOrderDetails foodOrderDetails) {
        foodOrderDetailsRepository.save(foodOrderDetails);
        return foodOrderDetails;
    }

    @Override
    public Optional<FoodOrderDetails> findById(int id) {
        return foodOrderDetailsRepository.findById(id);
    }

    @Override
    public List<FoodOrderDetails> findByOrderId(int orderId) {
        return foodOrderDetailsRepository.findByOrderId(orderId);
    }

    @Override
    public List<FoodOrderDetails> findAll(int page, int size) {
        return foodOrderDetailsRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return foodOrderDetailsRepository.count();
    }

    @Override
    @Transactional
    public int update(FoodOrderDetails foodOrderDetails) {
        return foodOrderDetailsRepository.update(foodOrderDetails);
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        return foodOrderDetailsRepository.deleteById(id);
    }
}

