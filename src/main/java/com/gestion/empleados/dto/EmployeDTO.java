package com.gestion.empleados.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeDTO {
    private String name;
    private String lastName;
    private String email;
    private String sexo;
    private double salary;
    private Date fecha;
}
