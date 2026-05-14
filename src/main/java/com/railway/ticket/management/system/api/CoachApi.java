package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Coach;
import com.railway.ticket.management.system.service.implementation.CoachService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coach")
public class CoachApi {

    private final CoachService coachService;

    public CoachApi(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping
    public void save(@RequestBody Coach coach) {this.coachService.save(coach);}

    @PutMapping
    public void update(@RequestBody Coach coach) {this.coachService.update(coach);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.coachService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<Coach> findById(@PathVariable int id) {return this.coachService.findById(id);}

    @GetMapping("/{page}/{size}")
    public List<Coach> findAll(@PathVariable int page, @PathVariable int size) {
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
