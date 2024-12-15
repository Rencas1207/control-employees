package com.gestion.empleados.services.impl;

import com.gestion.empleados.entities.Employer;
import com.gestion.empleados.repository.EmployerRepository;
import com.gestion.empleados.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Employer> findAll() {
        return employerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employer> findAll(Pageable pageable) {
        return employerRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Employer employer) {
        employerRepository.save(employer);
    }

    @Override
    @Transactional(readOnly = true)
    public Employer findOne(Long id) {
        return employerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        employerRepository.deleteById(id);
    }
}
