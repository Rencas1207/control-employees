package com.gestion.empleados.services.impl;

import com.gestion.empleados.dto.EmployeDTO;
import com.gestion.empleados.entities.ResponseApp;
import com.gestion.empleados.utils.excel.ValidationsExcel;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl {

    public ResponseApp<String> leerArchivoExcel(MultipartFile file) throws IOException {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<EmployeDTO> employees = new ArrayList<>();

            List<String> errores = ValidationsExcel.validateExcelAndGetEmploye(sheet, employees);
            workbook.close();

            if(!errores.isEmpty()) {
                return ResponseApp.<String>builder()
                    .success(false)
                    .message("Existen errores en el archivo excel.")
                    .response(errores.toString())
                    .build();
            }



            ResponseApp responseDooLatamEntity = ResponseApp.builder()
                .success(true)
                .message("Los empleados han sido recibidas con éxito.")
                .response(employees)
                .build();

            return responseDooLatamEntity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
