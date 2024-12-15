package com.gestion.empleados.controllers;

import com.gestion.empleados.entities.Employer;
import com.gestion.empleados.services.EmployerService;
import com.gestion.empleados.utils.pagination.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @GetMapping({"/", "/list", ""})
    public Page<Employer> listEmployes(
        @RequestParam(name = "page", defaultValue = "0") int page)
    {
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Employer> employers = employerService.findAll(pageRequest);
        PageRender<Employer> pageRender = new PageRender<>("/list", employers);
        return employers;
    }
}
