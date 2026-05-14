package com.railway.ticket.management.system.repository;

import com.railway.ticket.management.system.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    int save(User user);
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    List<User> findAll(int  page, int size);
    int count();
    int update(User user);
    int deleteById(int id);
}
