package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Schedule;
import com.railway.ticket.management.system.service.implementation.ScheduleService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleApi {

    private final ScheduleService scheduleService;

    public ScheduleApi(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public void save(@Valid @RequestBody Schedule schedule) {this.scheduleService.save(schedule);}

    @PutMapping
    public void update(@Valid @RequestBody Schedule schedule) {this.scheduleService.update(schedule);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        int result = this.scheduleService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> findById(@PathVariable int id) {
        return this.scheduleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Schedule> findAll(@RequestParam int page, @RequestParam int size) {
        return this.scheduleService.findAll(page, size);
    }

    @GetMapping("/train/{trainId}")
    public List<Schedule> findByTrainId(@PathVariable int trainId) {
        return this.scheduleService.findByTrainId(trainId);
    }

    @GetMapping("/source/{stationId}")
    public List<Schedule> findBySource(@PathVariable int stationId) {
        return this.scheduleService.findBySourceStationId(stationId);
    }

    @GetMapping("/destination/{stationId}")
    public List<Schedule> findByDestination(@PathVariable int stationId) {
        return this.scheduleService.findByDestinationStationId(stationId);
    }

    @PutMapping("/time/{scheduleId}/{departure}/{arrival}")
    public void updateTime(@PathVariable int scheduleId,
                           @PathVariable String departure,
                           @PathVariable String arrival) {
        this.scheduleService.updateScheduleTime(scheduleId,
                LocalDateTime.parse(departure), LocalDateTime.parse(arrival));
    }
}
