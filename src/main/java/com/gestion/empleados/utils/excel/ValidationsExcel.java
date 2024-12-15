package com.gestion.empleados.utils.excel;

import com.gestion.empleados.dto.EmployeDTO;
import com.gestion.empleados.models.Employe;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ValidationsExcel {

    public static List<String>  validateExcelAndGetEmploye(Sheet sheet, List<EmployeDTO> employeDTOList) {
        List<String> errores = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            EmployeDTO empleado = new EmployeDTO();
            try {
                empleado.setName(getStringCellValue(row, 0, "Nombre", errores));
                empleado.setLastName(getStringCellValue(row, 1, "Apellido", errores));
                empleado.setEmail(getStringCellValue(row, 2, "Email", errores));
                empleado.setSexo(getSexoCellValue(row, 3, "Sexo", errores));
                empleado.setSalary(getNumericCellValue(row, 4, "Salario", errores));
                empleado.setFecha(getDateCellValue(row, 5, "Fecha", errores));

                if (errores.stream().noneMatch(error -> error.contains("fila " + (row.getRowNum() + 1)))) {
                    employeDTOList.add(empleado);
                }
            } catch (Exception e) {
                errores.add("Error en la fila " + (row.getRowNum() + 1) + ": " + e.getMessage());
            }
        }
        return errores;
    }

    private static String getStringCellValue(Row row, int cellIndex, String fieldName, List<String> errores) {
        try {
            Cell cell = row.getCell(cellIndex);
            if (cell == null || cell.getStringCellValue().isBlank()) {
                throw new IllegalArgumentException(fieldName + " vacío en la fila " + (row.getRowNum() + 1));
            }
            return cell.getStringCellValue();
        } catch (Exception e) {
            errores.add(e.getMessage());
            return null;
        }
    }

    private static String getSexoCellValue(Row row, int cellIndex, String fieldName, List<String> errores) {
        try {
            Cell cell = row.getCell(cellIndex);
            if (cell == null || cell.getStringCellValue().isBlank()) {
                throw new IllegalArgumentException(fieldName + " vacío en la fila " + (row.getRowNum() + 1));
            }

            String value = cell.getStringCellValue().trim().toUpperCase();
            if (!value.equals("M") && !value.equals("F")) {
                throw new IllegalArgumentException(
                        fieldName + " debe ser 'M' o 'F' en la fila " + (row.getRowNum() + 1) + ". Valor encontrado: " + value
                );
            }
            return value;
        } catch (Exception e) {
            errores.add(e.getMessage());
            return null;
        }
    }

    private static double getNumericCellValue(Row row, int cellIndex, String fieldName, List<String> errores) {
        try {
            Cell cell = row.getCell(cellIndex);
            if (cell == null) {
                throw new IllegalArgumentException(fieldName + " vacío en la fila " + (row.getRowNum() + 1));
            }
            return cell.getNumericCellValue();
        } catch (Exception e) {
            errores.add(fieldName + " inválido en la fila " + (row.getRowNum() + 1));
            return 0.0;
        }
    }

    private static Date getDateCellValue(Row row, int cellIndex, String fieldName, List<String> errores) {
        try {
            Cell cell = row.getCell(cellIndex);
            if (cell == null) {
                throw new IllegalArgumentException(fieldName + " vacío en la fila " + (row.getRowNum() + 1));
            }
            return cell.getDateCellValue();
        } catch (Exception e) {
            errores.add(fieldName + " inválido en la fila " + (row.getRowNum() + 1));
            return null;
        }
    }
}
