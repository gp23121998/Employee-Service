package com.example.Employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Employee_Table")
public class Employee {
    @Id
    @Column(name="Employee_ID")
    private int employeeId;
    @Column(name="Department_ID")
    private int departmentId;
    @Column(name="Project_ID")
    private int projectId;
    @Column(name="Employee_Name")
    private String employeeName;
    @Column(name ="Employee_Email")
    private String employeeEmail;
    @Transient
    private Department department;
    @Transient
    private List<Project>projects =new ArrayList<>();

}
