package com.example.Employeeservice.service;
import com.example.Employeeservice.entity.Department;
import com.example.Employeeservice.entity.Employee;
import com.example.Employeeservice.repository.EmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.Employeeservice.exception.ResourceNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepo repo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${deparmentInfoAPI}")
    private String baseUrlforDepartment;

    @Value("${projectInfoAPI}")
    private String baseUrlforProject;


    @Override
    public Employee getEmployeeById(int id)  {
        Employee employee = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", id));
        //get department
        ResponseEntity<Department> departmentResponseEntity = restTemplate.getForEntity(baseUrlforDepartment+employee.getDepartmentId(), Department.class);
        ArrayList projectList = restTemplate.getForEntity(baseUrlforProject + employee.getEmployeeId(), ArrayList.class).getBody();
        employee.setDepartment(departmentResponseEntity.getBody());
        employee.setProjects(projectList);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> allEmployee = repo.findAll();
        List<Employee> listOfEmployee = new ArrayList<>();
        for (Employee employee : allEmployee) {
            ResponseEntity<Department> departmentResponseEntity = restTemplate.getForEntity(baseUrlforDepartment + employee.getDepartmentId(), Department.class);
            employee.setDepartment(departmentResponseEntity.getBody());
            listOfEmployee.add(employee);
        }
        return listOfEmployee;
    }

    @Override
    public List<Employee> getEmployeeByDepartmentId(int departmentId) {
        List<Employee> allEmployeeByDepartment = repo.findByDepartmentId(departmentId);
        return allEmployeeByDepartment;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        Employee save = repo.save(employee);
        return save;
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        Employee emp = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", id));
        emp.setEmployeeName(employee.getEmployeeName());
        Employee save = repo.save(emp);
        return save;
    }

    @Override
    public void deleteEmployee(int id) {
      repo.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeeByProjectId(int projectId) {
        List<Employee> employeebyProjectIdList = repo.findByProjectId(projectId);
        return employeebyProjectIdList;
    }

}
