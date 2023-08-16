package com.example.Employeeservice.service;


import com.example.Employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {

    //getEmployeeById (get)
    public Employee getEmployeeById(int id) ;

    public List<Employee> getAllEmployee();

    public List<Employee>getEmployeeByDepartmentId(int departmentId);

    //addEmployee (post)
    public Employee addEmployee(Employee employee);

    //updateEmployee (put)
    public Employee updateEmployee(Employee employee, int id);

    //deleteEmployee(delete)
    public void deleteEmployee(int id);

   public List<Employee> getEmployeeByProjectId(int projectId);
}
