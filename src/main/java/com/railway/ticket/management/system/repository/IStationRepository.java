package com.railway.ticket.management.system.repository;

import com.railway.ticket.management.system.domain.Station;

import java.util.List;
import java.util.Optional;

public interface IStationRepository {
    int save(Station station);
    Optional<Station> findById(int id);
    Optional<Station> findByStationCode(String stationCode);
    List<Station> findAll(int page, int size);
    int count();
    int update(Station station);
    int deleteById(int id);
}
