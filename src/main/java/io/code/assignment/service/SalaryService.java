package io.code.assignment.service;

import io.code.assignment.model.Employee;
import io.code.assignment.model.Hierarchy;

import java.util.Map;

public interface SalaryService {
    Map<Employee, Integer> getManagersEarningOk();
    Map<Employee, Integer> getManagersEarningLess();
    Map<Employee, Integer> getManagersEarningMore();
}
