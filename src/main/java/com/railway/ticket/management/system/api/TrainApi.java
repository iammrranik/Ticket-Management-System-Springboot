package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Train;
import com.railway.ticket.management.system.service.implementation.TrainService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
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
    public void save(@Valid @RequestBody Train train) {this.trainService.save(train);}

    @PutMapping
    public void update(@Valid @RequestBody Train train) {this.trainService.update(train);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        int result = this.trainService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> findById(@PathVariable int id) {
        return this.trainService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Train> findAll(@RequestParam int page, @RequestParam int size) {
        return this.trainService.findAll(page, size);
    }

    @GetMapping("/number/{regNumber}")
    public Optional<Train> findByRegNumber(@PathVariable String regNumber) {
        return this.trainService.findByTrainRegistrationNumber(regNumber);
    }
}
