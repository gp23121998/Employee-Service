package com.example.Employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private int departmentId;
    private int employeeId;
    private String departmentName;
}
