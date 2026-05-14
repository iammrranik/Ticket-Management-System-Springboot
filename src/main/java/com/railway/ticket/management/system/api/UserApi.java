package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.User;
import com.railway.ticket.management.system.service.implementation.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void save(@RequestBody User user) {this.userService.save(user);}

    @PutMapping
    public void update(@RequestBody User user) {this.userService.update(user);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {this.userService.deleteById(id);}

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable int id) {return this.userService.findById(id);}

    @GetMapping("/{page}/{size}")
    public List<User> findAll(@PathVariable int page, @PathVariable int size) {
        return this.userService.findAll(page, size);
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {this.userService.save(user);}

    @PostMapping("/login")
    public Optional<User> login(@RequestBody User user) {
        return this.userService.login(user.getUsername(), user.getPassword());
    }
}
