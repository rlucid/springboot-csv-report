package com.example.springboot_csv_report.model;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class User {
    private long id;
    private String firstName;
    private String lastName;
}
