package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.Station;
import com.railway.ticket.management.system.repository.implementation.StationRepository;
import com.railway.ticket.management.system.service.IStationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService implements IStationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station save(Station station) {
        stationRepository.save(station);
        return station;
    }

    @Override
    public Optional<Station> findById(int id) {
        return stationRepository.findById(id);
    }

    @Override
    public Optional<Station> findByStationCode(String stationCode) {
        return stationRepository.findByStationCode(stationCode);
    }

    @Override
    public List<Station> findAll(int page, int size) {
        return stationRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return stationRepository.count();
    }

    @Override
    public int update(Station station) {
        return stationRepository.update(station);
    }

    @Override
    public int deleteById(int id) {
        return stationRepository.deleteById(id);
    }
}
