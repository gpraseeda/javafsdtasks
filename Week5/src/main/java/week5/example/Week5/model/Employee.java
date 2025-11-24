package com.example.employeeapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Employee {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Salary is required")
    private Double salary;
}
