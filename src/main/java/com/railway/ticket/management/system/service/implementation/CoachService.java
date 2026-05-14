package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.Coach;
import com.railway.ticket.management.system.repository.implementation.CoachRepository;
import com.railway.ticket.management.system.service.ICoachService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService implements ICoachService {

    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public Coach save(Coach coach) {
        coachRepository.save(coach);
        return coach;
    }

    @Override
    public Optional<Coach> findById(int id) {
        return coachRepository.findById(id);
    }

    @Override
    public List<Coach> findByTrainId(int trainId) {
        return coachRepository.findByTrainId(trainId);
    }

    @Override
    public List<Coach> findAll(int page, int size) {
        return coachRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return coachRepository.count();
    }

    @Override
    public int update(Coach coach) {
        return coachRepository.update(coach);
    }

    @Override
    public int deleteById(int id) {
        return coachRepository.deleteById(id);
    }

    @Override
    public int updateCoachBaseFare(int coachId, float newFare) {
        return coachRepository.updateCoachBaseFare(coachId, newFare);
    }
}
