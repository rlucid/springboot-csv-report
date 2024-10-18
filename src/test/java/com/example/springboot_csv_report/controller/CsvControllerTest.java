package com.example.springboot_csv_report.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CsvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String CSV_REPORT_PATH = "/api/v1/csv-report";

    @Test
    void downloadCsv() throws Exception {
        // Act
        MockHttpServletResponse response = mockMvc.perform(get(CSV_REPORT_PATH.concat("/download")))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        // Assert
        assertEquals(response.getContentType(), "text/csv");
        assertEquals(response.getHeader("Content-Disposition"), "attachment; filename=report.csv");
        System.out.println(response.getContentAsString());
    }
}