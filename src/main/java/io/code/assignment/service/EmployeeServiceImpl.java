package io.code.assignment.service;

import io.code.assignment.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees;

    private final Map<Integer, Employee> employeeMap = new HashMap<>();

    private final Map<Integer, List<Employee>> employeesByManager = new HashMap<>();


    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        if (!this.employeeMap.isEmpty()) {
            /* avoid recalculation on consecutive calls */
            return this.employeeMap;
        } else {
            /* prepare all-employees data with depth pre-calculated */
            this.employees.forEach(e -> this.employeeMap.put(e.getId(), e));

            employees.forEach(e -> {
                int depth = 0;
                Integer managerId = e.getManagerId();
                while (managerId != 0) {
                    depth++;
                    Employee manager = this.employeeMap.get(managerId);
                    if (manager == null) break;
                    managerId = manager.getManagerId();
                }
                e.setDepth(depth);
            });
            return this.employeeMap;
        }
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByManager() {
        if (!this.employeesByManager.isEmpty()) {
            /* avoid recalculation on consecutive calls */
            return this.employeesByManager;
        } else {
            this.employees.forEach(e -> {
                Integer managerId = e.getManagerId();
                if (managerId != 0) {
                    if (!this.employeesByManager.containsKey(managerId)) {
                        this.employeesByManager.put(managerId, new ArrayList<>());
                    }
                    employeesByManager.get(managerId).add(e);
                }
            });
            return this.employeesByManager;
        }
    }
}
