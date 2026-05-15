package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.TrainStatus;
import com.railway.ticket.management.system.service.implementation.TrainStatusService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/train-status")
public class TrainStatusApi {

    private final TrainStatusService trainStatusService;

    public TrainStatusApi(TrainStatusService trainStatusService) {
        this.trainStatusService = trainStatusService;
    }

    @PostMapping
    public void save(@Valid @RequestBody TrainStatus trainStatus) {
        this.trainStatusService.save(trainStatus);
    }

    @PutMapping
    public void update(@Valid @RequestBody TrainStatus trainStatus) {
        this.trainStatusService.update(trainStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        int result = this.trainStatusService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainStatus> findById(@PathVariable int id) {
        return this.trainStatusService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TrainStatus> findAll(@RequestParam int page, @RequestParam int size) {
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
