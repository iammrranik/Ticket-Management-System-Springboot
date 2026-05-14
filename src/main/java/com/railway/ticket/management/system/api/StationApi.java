package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Station;
import com.railway.ticket.management.system.service.implementation.StationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/station")
public class StationApi {

    private final StationService stationService;

    public StationApi(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public void save(@RequestBody Station station) {this.stationService.save(station);}

    @PutMapping
    public void update(@RequestBody Station station) {this.stationService.update(station);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.stationService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<Station> findById(@PathVariable int id) {return this.stationService.findById(id);}

    @GetMapping("/{page}/{size}")
    public List<Station> findAll(@PathVariable int page, @PathVariable int size) {
        return this.stationService.findAll(page, size);
    }

    @GetMapping("/code/{code}")
    public Optional<Station> findByCode(@PathVariable String code) {
        return this.stationService.findByStationCode(code);
    }
}
