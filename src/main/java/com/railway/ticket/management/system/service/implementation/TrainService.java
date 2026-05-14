package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.Train;
import com.railway.ticket.management.system.repository.implementation.TrainRepository;
import com.railway.ticket.management.system.service.ITrainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService implements ITrainService {

    private final TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public Train save(Train train) {
        trainRepository.save(train);
        return train;
    }

    @Override
    public Optional<Train> findById(int id) {
        return trainRepository.findById(id);
    }

    @Override
    public Optional<Train> findByTrainRegistrationNumber(String trainRegistrationNumber) {
        return trainRepository.findByTrainRegistrationNumber(trainRegistrationNumber);
    }

    @Override
    public List<Train> findAll(int page, int size) {
        return trainRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return trainRepository.count();
    }

    @Override
    public int update(Train train) {
        return trainRepository.update(train);
    }

    @Override
    public int deleteById(int id) {
        return trainRepository.deleteById(id);
    }
}
