package com.example.Employeeservice.service;

import com.example.Employeeservice.controller.EmployeeController;
import com.example.Employeeservice.entity.Department;
import com.example.Employeeservice.entity.Employee;
import com.example.Employeeservice.repository.EmployeeRepo;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.HttpExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class EmployeeDaprComponent {

    @Autowired
    private DaprClient daprClient;

    @Autowired
    private EmployeeRepo repo;

    @Value("${departmentdaprId}")
    private String departmentDaprId;

    @Value("${departmentMethodName}")
    private String departmentMethodName;

    @Value("${projectdaprId}")
    private String projectDaprId;

    @Value("${projectMethodName}")
    private String projectMethodName;

    private static final Logger log = LoggerFactory.getLogger(EmployeeDaprComponent.class);

    public Mono<Department> invokeGetDepartmentById(int departmentId){
        return daprClient.invokeMethod(departmentDaprId,departmentMethodName+departmentId,
                null,HttpExtension.GET,Department.class);
    }

    public Mono<ArrayList> invokeGetProjectsById(int employeeId){
        return daprClient.invokeMethod(projectDaprId, projectMethodName + employeeId, null, HttpExtension.GET, ArrayList.class);
    }

    public Optional<Employee> getEmployeeById(int employeeId){
        Optional<Employee> employee = repo.findById(employeeId);
        log.info("employee*****",employee.get());
        if(employee.isPresent()){
            employee.get().setDepartment(invokeGetDepartmentById(employee.get().getDepartmentId()).block());
            log.info("department******",employee.get().getDepartment());
            employee.get().setProjects(invokeGetProjectsById(employeeId).block());
            log.info("project******",employee.get().getProjects());
        }
        return employee;
    }

}
