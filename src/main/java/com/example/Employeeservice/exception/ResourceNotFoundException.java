package com.example.Employeeservice.exception;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    int fieldValue;
    public ResourceNotFoundException(String employee, String id, int id1) {
        super(String.format("%s not Found with %s :%s",employee,id,id1));
        this.resourceName=employee;
        this.fieldName=id;
        this.fieldValue=id1;

    }
}
