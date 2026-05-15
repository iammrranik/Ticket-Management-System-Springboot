package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Coach;
import com.railway.ticket.management.system.service.implementation.CoachService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coach")
public class CoachApi {

    private final CoachService coachService;

    public CoachApi(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping
    public void save(@Valid @RequestBody Coach coach) {this.coachService.save(coach);}

    @PutMapping
    public void update(@Valid @RequestBody Coach coach) {this.coachService.update(coach);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        int result = this.coachService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> findById(@PathVariable int id) {
        return this.coachService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Coach> findAll(@RequestParam int page, @RequestParam int size) {
        return this.coachService.findAll(page, size);
    }

    @GetMapping("/train/{trainId}")
    public List<Coach> findByTrainId(@PathVariable int trainId) {
        return this.coachService.findByTrainId(trainId);
    }

    @PutMapping("/fare/{coachId}/{newFare}")
    public void updateFare(@PathVariable int coachId, @PathVariable float newFare) {
        this.coachService.updateCoachBaseFare(coachId, newFare);
    }
}
