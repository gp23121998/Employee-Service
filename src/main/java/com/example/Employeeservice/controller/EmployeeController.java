package com.example.Employeeservice.controller;

import com.example.Employeeservice.entity.Employee;
import com.example.Employeeservice.service.EmployeeDaprComponent;
import com.example.Employeeservice.service.EmployeeServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImp service;

    @Autowired
    private EmployeeDaprComponent employeeDapr;

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    //post
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee employee1 = service.addEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }

    //get
    @GetMapping("/restcall/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee employeeById = service.getEmployeeById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    //getEmployeebydepartmentId
    @GetMapping("/departmentid/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable int departmentId){
        List<Employee> employeeByDepartmentId = service.getEmployeeByDepartmentId(departmentId);
        return new ResponseEntity<>(employeeByDepartmentId, HttpStatus.OK);
    }

    //getEmployeebyprojectId
    @GetMapping("/projectid/{projectId}")
    public ResponseEntity<List<Employee>> getEmployeesByProjectId(@PathVariable int projectId){
        List<Employee> employeeByProjectId = service.getEmployeeByProjectId(projectId);
        return new ResponseEntity<>(employeeByProjectId, HttpStatus.OK);
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

    @GetMapping("/daprcall/{employeeId}")
    public ResponseEntity<?>getEmployeeByDapr(@PathVariable int employeeId){
        Optional<Employee> employeeById = employeeDapr.getEmployeeById(employeeId);
        if(employeeById.isEmpty()){
            return new ResponseEntity<>("Employee not exist with the Id : "+employeeId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeById,HttpStatus.OK);
    }


}
