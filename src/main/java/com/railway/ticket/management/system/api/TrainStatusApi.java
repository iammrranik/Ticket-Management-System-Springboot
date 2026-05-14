package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.TrainStatus;
import com.railway.ticket.management.system.service.implementation.TrainStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/train-status")
public class TrainStatusApi {

    private final TrainStatusService trainStatusService;

    public TrainStatusApi(TrainStatusService trainStatusService) {
        this.trainStatusService = trainStatusService;
    }

    @PostMapping
    public void save(@RequestBody TrainStatus trainStatus) {
        this.trainStatusService.save(trainStatus);
    }

    @PutMapping
    public void update(@RequestBody TrainStatus trainStatus) {
        this.trainStatusService.update(trainStatus);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.trainStatusService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<TrainStatus> findById(@PathVariable int id) {
        return this.trainStatusService.findById(id);
    }

    @GetMapping("/{page}/{size}")
    public List<TrainStatus> findAll(@PathVariable int page, @PathVariable int size) {
        return this.trainStatusService.findAll(page, size);
    }

    @GetMapping("/train/{trainId}")
    public List<TrainStatus> findByTrainId(@PathVariable int trainId) {
        return this.trainStatusService.findByTrainId(trainId);
    }

    @PutMapping("/location/{trainId}/{currentStationId}/{nextStationId}/{status}")
    public void updateLocation(@PathVariable int trainId,
                                @PathVariable int currentStationId,
                                @PathVariable int nextStationId,
                                @PathVariable String status) {
        this.trainStatusService.updateTrainLocation(trainId, currentStationId, nextStationId, status);
    }
}
