package com.example.Employeeservice.controller;

import com.example.Employeeservice.entity.Employee;
import com.example.Employeeservice.service.EmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImp service;

    //post
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee employee1 = service.addEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }

    //get
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee employeeById = service.getEmployeeById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.FOUND);
    }

    //getEmployeebydepartmentId
    @GetMapping("/departmentid/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable int departmentId){
        List<Employee> employeeByDepartmentId = service.getEmployeeByDepartmentId(departmentId);
        return new ResponseEntity<>(employeeByDepartmentId, HttpStatus.FOUND);
    }

    //getEmployeebyprojectId
    @GetMapping("/projectid/{projectId}")
    public ResponseEntity<List<Employee>> getEmployeesByProjectId(@PathVariable int projectId){
        List<Employee> employeeByProjectId = service.getEmployeeByProjectId(projectId);
        return new ResponseEntity<>(employeeByProjectId, HttpStatus.FOUND);
    }

    //GetAll
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> allEmployee = service.getAllEmployee();
        return new ResponseEntity<>(allEmployee, HttpStatus.OK);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee emp = service.updateEmployee(employee,id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
        service.deleteEmployee(id);
        return new ResponseEntity<>(Map.of("message","Employee is deleted"),HttpStatus.NO_CONTENT);
    }


}
