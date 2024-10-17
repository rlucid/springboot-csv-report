package com.example.springboot_csv_report.service;


import com.example.springboot_csv_report.model.User;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CsvService {

    public void generateCsvReport(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=report.csv");

        CSVWriter csvWriter = new CSVWriter(response.getWriter());

        String[] headers = new String[]{"ID", "FirstName", "LastName"};
        csvWriter.writeNext(headers);

        List<User> users = generateUsers();
        for (User user : users) {
            String[] values =
                    new String[]{String.valueOf(user.getId()), user.getFirstName(), user.getLastName()};
            csvWriter.writeNext(values);
        }
    }

    private static List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John", "Doe"));
        users.add(new User(2, "John", "Wick"));
        users.add(new User(3, "Jet", "Li"));
        return users;
    }
}
