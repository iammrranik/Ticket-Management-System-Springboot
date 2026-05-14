package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.ReturnPolicy;
import com.railway.ticket.management.system.repository.implementation.ReturnPolicyRepository;
import com.railway.ticket.management.system.service.IReturnPolicyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReturnPolicyService implements IReturnPolicyService {

    private final ReturnPolicyRepository returnPolicyRepository;

    public ReturnPolicyService(ReturnPolicyRepository returnPolicyRepository) {
        this.returnPolicyRepository = returnPolicyRepository;
    }

    @Override
    public ReturnPolicy save(ReturnPolicy returnPolicy) {
        returnPolicyRepository.save(returnPolicy);
        return returnPolicy;
    }

    @Override
    public Optional<ReturnPolicy> findById(int id) {
        return returnPolicyRepository.findById(id);
    }

    @Override
    public List<ReturnPolicy> findAll(int page, int size) {
        return returnPolicyRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return returnPolicyRepository.count();
    }

    @Override
    public int update(ReturnPolicy returnPolicy) {
        return returnPolicyRepository.update(returnPolicy);
    }

    @Override
    public int deleteById(int id) {
        return returnPolicyRepository.deleteById(id);
    }
}
