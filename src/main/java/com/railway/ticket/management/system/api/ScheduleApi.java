package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Schedule;
import com.railway.ticket.management.system.service.implementation.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleApi {

    private final ScheduleService scheduleService;

    public ScheduleApi(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public void save(@RequestBody Schedule schedule) {this.scheduleService.save(schedule);}

    @PutMapping
    public void update(@RequestBody Schedule schedule) {this.scheduleService.update(schedule);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.scheduleService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<Schedule> findById(@PathVariable int id) {return this.scheduleService.findById(id);}

    @GetMapping("/{page}/{size}")
    public List<Schedule> findAll(@PathVariable int page, @PathVariable int size) {
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
