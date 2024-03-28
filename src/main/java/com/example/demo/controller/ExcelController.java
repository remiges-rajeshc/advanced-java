package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ExcelController {
    @Value("${excel.file.prefix}")
    private String excelFilePrefix;

    @Value("${excel.file.directory}")
    private String excelFileDirectory;

    @GetMapping("/generate-excel")
    public ResponseEntity<?> generateExcelFile() {
        String filename = excelFilePrefix + LocalDateTime.now() + ".xlsx";
        String filepath = excelFileDirectory + "/" + filename;

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Hello, Excel!");

            try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
                workbook.write(outputStream);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(
                    MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(headers, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>("Failed to generate Excel file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
