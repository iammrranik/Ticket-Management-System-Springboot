package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.Schedule;
import com.railway.ticket.management.system.repository.implementation.ScheduleRepository;
import com.railway.ticket.management.system.service.IScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService implements IScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    @Transactional
    public Schedule save(Schedule schedule) {
        scheduleRepository.save(schedule);
        return schedule;
    }

    @Override
    public Optional<Schedule> findById(int id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> findByTrainId(int trainId) {
        return scheduleRepository.findByTrainId(trainId);
    }

    @Override
    public List<Schedule> findBySourceStationId(int sourceStationId) {
        return scheduleRepository.findBySourceStationId(sourceStationId);
    }

    @Override
    public List<Schedule> findByDestinationStationId(int destinationStationId) {
        return scheduleRepository.findByDestinationStationId(destinationStationId);
    }

    @Override
    public List<Schedule> findAll(int page, int size) {
        return scheduleRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return scheduleRepository.count();
    }

    @Override
    @Transactional
    public int update(Schedule schedule) {
        return scheduleRepository.update(schedule);
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        return scheduleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public int updateScheduleTime(int scheduleId, LocalDateTime newDeparture, LocalDateTime newArrival) {
        return scheduleRepository.updateScheduleTime(scheduleId, newDeparture, newArrival);
    }
}
