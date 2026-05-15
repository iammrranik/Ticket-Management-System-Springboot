package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Station;
import com.railway.ticket.management.system.service.implementation.StationService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
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
    public void save(@Valid @RequestBody Station station) {this.stationService.save(station);}

    @PutMapping
    public void update(@Valid @RequestBody Station station) {this.stationService.update(station);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        int result = this.stationService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Station> findById(@PathVariable int id) {
        return this.stationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Station> findAll(@RequestParam int page, @RequestParam int size) {
        return this.stationService.findAll(page, size);
    }

    @GetMapping("/code/{code}")
    public Optional<Station> findByCode(@PathVariable String code) {
        return this.stationService.findByStationCode(code);
    }
}
