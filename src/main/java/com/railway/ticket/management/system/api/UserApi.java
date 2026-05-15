package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.User;
import com.railway.ticket.management.system.security.JwtUtil;
import com.railway.ticket.management.system.service.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserApi(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public void save(@Valid @RequestBody User user) {this.userService.save(user);}

    @PutMapping
    public void update(@Valid @RequestBody User user) {this.userService.update(user);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        int result = this.userService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        return this.userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<User> findAll(@RequestParam int page, @RequestParam int size) {
        return this.userService.findAll(page, size);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody User user) {
        return this.userService.login(user.getUsername(), user.getPassword())
                .map(u -> {
                    String token = jwtUtil.generateToken(u.getUsername(), u.getRole().name());
                    Map<String, Object> dto = new HashMap<>();
                    dto.put("token", token);
                    dto.put("id", u.getId());
                    dto.put("username", u.getUsername());
                    dto.put("email", u.getEmail());
                    dto.put("fullName", u.getFullName());
                    dto.put("role", u.getRole());
                    dto.put("createdAt", u.getCreatedAt());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.status(401).build());
    }
}
