package com.railway.ticket.management.system.service;

import com.railway.ticket.management.system.domain.TrainStatus;
import com.railway.ticket.management.system.domain.enums.TrainStatusEnum;

import java.util.List;
import java.util.Optional;

public interface ITrainStatusService {
    TrainStatus save(TrainStatus trainStatus);
    Optional<TrainStatus> findById(int id);
    List<TrainStatus> findByTrainId(int trainId);
    List<TrainStatus> findAll(int page, int size);
    int count();
    int update(TrainStatus trainStatus);
    int deleteById(int id);
    int updateTrainLocation(int trainId, int currentStationId, int nextStationId, TrainStatusEnum status);
}
