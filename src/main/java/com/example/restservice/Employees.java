package com.example.restservice;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employees {

    @JsonProperty("employeeList")
    private List<Employee> employeeList;

    public Employees() {}

    public Employees(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}