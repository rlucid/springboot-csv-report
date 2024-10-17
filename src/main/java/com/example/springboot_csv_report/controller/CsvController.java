package com.example.springboot_csv_report.controller;


import com.example.springboot_csv_report.service.CsvService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("api/v1/csv-report")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @GetMapping("/download")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        csvService.generateCsvReport(response);
    }

    @GetMapping("/gen/download")
    public void downloadCsvGen(HttpServletResponse response) throws IOException, IllegalAccessException {
        csvService.generateCsvReportForListOfData(response);
    }
}
