package com.railway.ticket.management.system.service;

import com.railway.ticket.management.system.domain.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IScheduleService {
    Schedule save(Schedule schedule);
    Optional<Schedule> findById(int id);
    List<Schedule> findByTrainId(int trainId);
    List<Schedule> findBySourceStationId(int sourceStationId);
    List<Schedule> findByDestinationStationId(int destinationStationId);
    List<Schedule> findAll(int page, int size);
    int count();
    int update(Schedule schedule);
    int deleteById(int id);
    int updateScheduleTime(int scheduleId, LocalDateTime newDeparture, LocalDateTime newArrival);
}
