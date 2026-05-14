package com.railway.ticket.management.system.service;

import com.railway.ticket.management.system.domain.ReturnPolicy;

import java.util.List;
import java.util.Optional;

public interface IReturnPolicyService {
    ReturnPolicy save(ReturnPolicy returnPolicy);
    Optional<ReturnPolicy> findById(int id);
    List<ReturnPolicy> findAll(int page, int size);
    int count();
    int update(ReturnPolicy returnPolicy);
    int deleteById(int id);
}
