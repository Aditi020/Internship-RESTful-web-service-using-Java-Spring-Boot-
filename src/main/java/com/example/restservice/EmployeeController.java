package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeManager employeeManager;

    public EmployeeController(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @GetMapping("/employees")
    public Employees getEmployees() {
        return employeeManager.getEmployees();
    }

    @PostMapping("/employees")
    public Employees addEmployee(@RequestBody Employee newEmployee) {
        employeeManager.addEmployee(newEmployee);
        return employeeManager.getEmployees();
    }
}