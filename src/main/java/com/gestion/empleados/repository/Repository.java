package com.gestion.empleados.repository;

import com.gestion.empleados.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Employer, Long> {

}
