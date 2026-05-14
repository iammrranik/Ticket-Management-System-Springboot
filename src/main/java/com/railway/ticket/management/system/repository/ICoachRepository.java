package com.railway.ticket.management.system.repository;

import com.railway.ticket.management.system.domain.Coach;

import java.util.List;
import java.util.Optional;

public interface ICoachRepository {
    int save(Coach coach);
    Optional<Coach> findById(int id);
    List<Coach> findByTrainId(int trainId);
    List<Coach> findAll(int page, int size);
    int count();
    int update(Coach coach);
    int deleteById(int id);
    int updateCoachBaseFare(int coachId, float newFare);
}
