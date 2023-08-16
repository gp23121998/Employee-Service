package com.example.Employeeservice.repository;

import com.example.Employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    public List<Employee>findByDepartmentId(int departmentId);
    public List<Employee>findByProjectId(int projectId);
}
