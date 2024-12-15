package com.gestion.empleados.services;

import com.gestion.empleados.entities.Employer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployerService {
    public List<Employer> findAll();
    public Page<Employer> findAll(Pageable pageable);
    public void save(Employer employer);
    public Employer findOne(Long id);
    public void delete(Long id);
}
