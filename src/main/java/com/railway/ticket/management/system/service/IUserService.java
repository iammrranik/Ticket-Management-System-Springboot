package com.railway.ticket.management.system.service;

import com.railway.ticket.management.system.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User save(User user);
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    List<User> findAll(int page, int size);
    int count();
    int update(User user);
    int deleteById(int id);
    Optional<User> login(String username, String password);
}
