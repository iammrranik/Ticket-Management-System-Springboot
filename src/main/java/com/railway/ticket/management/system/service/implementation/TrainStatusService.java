package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.TrainStatus;
import com.railway.ticket.management.system.repository.implementation.TrainStatusRepository;
import com.railway.ticket.management.system.service.ITrainStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TrainStatusService implements ITrainStatusService {

    private final TrainStatusRepository trainStatusRepository;

    public TrainStatusService(TrainStatusRepository trainStatusRepository) {
        this.trainStatusRepository = trainStatusRepository;
    }

    @Override
    @Transactional
    public TrainStatus save(TrainStatus trainStatus) {
        trainStatusRepository.save(trainStatus);
        return trainStatus;
    }

    @Override
    public Optional<TrainStatus> findById(int id) {
        return trainStatusRepository.findById(id);
    }

    @Override
    public List<TrainStatus> findByTrainId(int trainId) {
        return trainStatusRepository.findByTrainId(trainId);
    }

    @Override
    public List<TrainStatus> findAll(int page, int size) {
        return trainStatusRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return trainStatusRepository.count();
    }

    @Override
    @Transactional
    public int update(TrainStatus trainStatus) {
        return trainStatusRepository.update(trainStatus);
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        return trainStatusRepository.deleteById(id);
    }

    @Override
    @Transactional
    public int updateTrainLocation(int trainId, int currentStationId, int nextStationId, String status) {
        return trainStatusRepository.updateTrainLocation(trainId, currentStationId, nextStationId, status);
    }
}
