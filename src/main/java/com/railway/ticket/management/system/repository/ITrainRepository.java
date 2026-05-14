package com.railway.ticket.management.system.repository;

import com.railway.ticket.management.system.domain.Train;

import java.util.List;
import java.util.Optional;

public interface ITrainRepository {
    int save(Train train);
    Optional<Train> findById(int id);
    Optional<Train> findByTrainRegistrationNumber(String trainRegistrationNumber);
    List<Train> findAll(int page, int size);
    int count();
    int update(Train train);
    int deleteById(int id);
}
