package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.ReturnPolicy;
import com.railway.ticket.management.system.service.implementation.ReturnPolicyService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/return-policy")
public class ReturnPolicyApi {

    private final ReturnPolicyService returnPolicyService;

    public ReturnPolicyApi(ReturnPolicyService returnPolicyService) {
        this.returnPolicyService = returnPolicyService;
    }

    @PostMapping
    public void save(@Valid @RequestBody ReturnPolicy returnPolicy) {
        System.out.println("POST /api/return-policy - " + returnPolicy.getPolicyName());
        this.returnPolicyService.save(returnPolicy);
    }

    @PutMapping
    public void update(@Valid @RequestBody ReturnPolicy returnPolicy) {
        System.out.println("PUT /api/return-policy - id=" + returnPolicy.getId());
        this.returnPolicyService.update(returnPolicy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        System.out.println("DELETE /api/return-policy/" + id);
        int result = this.returnPolicyService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnPolicy> findById(@PathVariable int id) {
        System.out.println("GET /api/return-policy/" + id);
        return this.returnPolicyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ReturnPolicy> findAll(@RequestParam int page, @RequestParam int size) {
        System.out.println("GET /api/return-policy?page=" + page + "&size=" + size);
        return this.returnPolicyService.findAll(page, size);
    }
}
