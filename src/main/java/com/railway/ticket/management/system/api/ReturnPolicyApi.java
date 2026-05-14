package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.ReturnPolicy;
import com.railway.ticket.management.system.service.implementation.ReturnPolicyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/return-policy")
public class ReturnPolicyApi {

    private final ReturnPolicyService returnPolicyService;

    public ReturnPolicyApi(ReturnPolicyService returnPolicyService) {
        this.returnPolicyService = returnPolicyService;
    }

    @PostMapping
    public void save(@RequestBody ReturnPolicy returnPolicy) {
        this.returnPolicyService.save(returnPolicy);
    }

    @PutMapping
    public void update(@RequestBody ReturnPolicy returnPolicy) {
        this.returnPolicyService.update(returnPolicy);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.returnPolicyService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<ReturnPolicy> findById(@PathVariable int id) {
        return this.returnPolicyService.findById(id);
    }

    @GetMapping("/{page}/{size}")
    public List<ReturnPolicy> findAll(@PathVariable int page, @PathVariable int size) {
        return this.returnPolicyService.findAll(page, size);
    }
}
