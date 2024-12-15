package com.gestion.empleados.controllers;

import com.gestion.empleados.entities.Employer;
import com.gestion.empleados.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Repository repo;

//    @GetMapping("/")
//    @Transactional(readOnly = true)
//    public String index() {
//        return "CONECTADO";
//    }

    @GetMapping("/empleados")
    @Transactional(readOnly = true)
    public List<Employer> getEmployers() {
        return repo.findAll();
    }

    @PostMapping("/save")
    @Transactional
    public String saveEmployer(@RequestBody Employer employer) {
        repo.save(employer);
        return "Empleado guardado";
    }

    @PutMapping("/edit/{id}")
    @Transactional(readOnly = true)
    public String editEmployer(@PathVariable Long id, @RequestBody Employer employer) {
        Employer employerUpdate = repo.findById(id).get();
        employerUpdate.setName(employer.getName());
        employerUpdate.setLastName(employer.getLastName());
        employerUpdate.setEmail(employer.getEmail());
        employerUpdate.setPhone(employer.getPhone());
        employerUpdate.setSexo(employer.getSexo());
        employerUpdate.setSalary(employer.getSalary());
        repo.save(employerUpdate);
        return "Empleado editado correctamente";
    }

    @DeleteMapping("/delete/{id}")
    @Transactional(readOnly = true)
    public String deleteEmployer(@PathVariable Long id) {
        repo.deleteById(id);
        return "Empleado eliminado";
    }
}

