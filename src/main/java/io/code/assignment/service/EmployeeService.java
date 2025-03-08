package io.code.assignment.service;

import io.code.assignment.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Map<Integer, Employee> getAllEmployees();
    Map<Integer, List<Employee>> getEmployeesByManager();
}
