package com.example.restservice;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeManager {

    private Employees employees;

    public EmployeeManager() {
        this.employees = new Employees(new ArrayList<>());
        addDefaultEmployees();
    }

    private void addDefaultEmployees() {
        employees.getEmployeeList().add(new Employee("1", "John", "Doe", "john.doe@example.com", "Software Engineer"));
        employees.getEmployeeList().add(new Employee("2", "Jane", "Smith", "jane.smith@example.com", "Product Manager"));
        employees.getEmployeeList().add(new Employee("3", "Emily", "Johnson", "emily.johnson@example.com", "Data Scientist"));
        employees.getEmployeeList().add(new Employee("4", "Michael", "Brown", "michael.brown@example.com", "UX Designer"));
    }

    public Employees getEmployees() {
        return employees;
    }

    public void addEmployee(Employee newEmployee) {
        employees.getEmployeeList().add(newEmployee);
    }
}