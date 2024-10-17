package com.example.springboot_csv_report.service;


import com.example.springboot_csv_report.model.User;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void generateCsvReportForListOfData(HttpServletResponse response) throws IOException, IllegalAccessException {
        List<User> users = generateUsers();
        generateCsvReportGeneric(response, users);

    }

    public <T> void generateCsvReportGeneric(HttpServletResponse response, List<T> list) throws IOException, IllegalAccessException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=gen-report.csv");

        CSVWriter csvWriter = new CSVWriter(response.getWriter());

        Field[]  fields = list.get(0).getClass().getDeclaredFields();
        String[] headers = getHeaders(fields);
        csvWriter.writeNext(headers);

        for (T object : list) {
            String[] values = getValues(fields, object);
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

    private String[] getHeaders(Field[] fields) {
        return Arrays.stream(fields).map(Field::getName).toArray(String[]::new);
    }

    private <T> String[] getValues(Field[] fields, T object) throws IllegalAccessException {
        String[] values = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            values[i] = field.get(object).toString();
        }
        return values;
    }
}
