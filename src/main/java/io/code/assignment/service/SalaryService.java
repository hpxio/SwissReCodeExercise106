package io.code.assignment.service;

import io.code.assignment.model.Employee;

import java.util.List;
import java.util.Map;

public interface SalaryService {

    List<Employee> getAllEmployees();

    Map<Employee, Long> getManagersEarningLess();

    Map<Employee, Long> getManagersEarningMore();

}
