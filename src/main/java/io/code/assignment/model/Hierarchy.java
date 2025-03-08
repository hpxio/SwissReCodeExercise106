package io.code.assignment.model;

import java.util.List;
import java.util.Map;

public class Hierarchy {
    private Map<Integer, Employee> employees;
    private Map<Integer, List<Employee>> managers;

    public Hierarchy(Map<Integer, Employee> employees,
                     Map<Integer, List<Employee>> managers) {
        this.employees = employees;
        this.managers = managers;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }

    public Map<Integer, List<Employee>> getManagers() {
        return managers;
    }

    public void setManagers(Map<Integer, List<Employee>> managers) {
        this.managers = managers;
    }
}
