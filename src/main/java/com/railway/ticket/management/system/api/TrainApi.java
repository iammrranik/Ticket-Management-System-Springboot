package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Train;
import com.railway.ticket.management.system.service.implementation.TrainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/train")
public class TrainApi {

    private final TrainService trainService;

    public TrainApi(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    public void save(@RequestBody Train train) {this.trainService.save(train);}

    @PutMapping
    public void update(@RequestBody Train train) {this.trainService.update(train);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.trainService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<Train> findById(@PathVariable int id) {return this.trainService.findById(id);}

    @GetMapping("/{page}/{size}")
    public List<Train> findAll(@PathVariable int page, @PathVariable int size) {
        return this.trainService.findAll(page, size);
    }

    @GetMapping("/number/{regNumber}")
    public Optional<Train> findByRegNumber(@PathVariable String regNumber) {
        return this.trainService.findByTrainRegistrationNumber(regNumber);
    }
}
