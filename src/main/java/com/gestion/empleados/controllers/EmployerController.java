package com.gestion.empleados.controllers;

import com.gestion.empleados.entities.Employer;
import com.gestion.empleados.entities.ResponseApp;
import com.gestion.empleados.services.EmployerService;
import com.gestion.empleados.services.impl.ExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private ExcelServiceImpl excelService;

    @GetMapping({"/", "/list", ""})
    public Page<Employer> listEmployes(
        @RequestParam(name = "page", defaultValue = "0") int page)
    {
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Employer> employers = employerService.findAll(pageRequest);
        return employers;
    }

    @GetMapping("/{id}")
    public Employer showEmployer(@PathVariable Long id) {
        Employer employer = employerService.findOne(id);
        return employer;
    }

    @PostMapping(value = "/upload_employees", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadEmployes(@RequestParam("file") MultipartFile file) {
        try {
            ResponseApp<String> employes = excelService.leerArchivoExcel(file);
            return new ResponseEntity<>(employes, HttpStatus.OK);
        } catch(RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(IOException e) {
            return new ResponseEntity<>("Error leyendo el archivo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
