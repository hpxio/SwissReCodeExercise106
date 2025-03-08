package io.code.assignment.service;

import io.code.assignment.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        /* mocked input data for assertions */
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Greg", "Kingsley", 10000, 0));
        employees.add(new Employee(2, "Jon", "Parsley", 20000, 1));
        employees.add(new Employee(3, "Kelly", "Johnson", 10000, 2));
        employees.add(new Employee(4, "Harry", "Potter", 30000, 3));
        employees.add(new Employee(5, "Lola", "Kyle", 15000, 4));
        employees.add(new Employee(6, "Linda", "Manning", 30000, 5));
        employees.add(new Employee(7, "Brett", "Lee", 25000, 6));

        employeeService = new EmployeeServiceImpl(employees);
    }

    @Test
    void testEmployeeServiceGetAllEmployees() {
        Map<Integer, Employee> allEmployees = employeeService.getAllEmployees();

        /* assert all data is loaded */
        assertEquals(7, allEmployees.size());

        /* assert depth calculation is correct */
        assertEquals(0, allEmployees.get(1).getDepth());
        assertEquals(1, allEmployees.get(2).getDepth());
        assertEquals(2, allEmployees.get(3).getDepth());
        assertEquals(3, allEmployees.get(4).getDepth());
        assertEquals(4, allEmployees.get(5).getDepth());
        assertEquals(5, allEmployees.get(6).getDepth());
        assertEquals(6, allEmployees.get(7).getDepth());

        /* check data is not recalculated on consecutive calls */
        Map<Integer, Employee> allEmployees1 = employeeService.getAllEmployees();
        assertEquals(7, allEmployees1.size());
    }

    @Test
    void testEmployeeServiceGetEmployeesByManager() {
        Map<Integer, List<Employee>> employeesByManager = employeeService.getEmployeesByManager();

        assertEquals(6, employeesByManager.size());

        List<Employee> manager1Employees = employeesByManager.get(1);
        assertNotNull(manager1Employees);
        assertEquals(1, manager1Employees.size()); /* Jon under Greg */

        List<Employee> manager2Employees = employeesByManager.get(2);
        assertNotNull(manager2Employees);
        assertEquals(1, manager2Employees.size()); /* Kelly under Jon */

        /* check data is not recalculated on consecutive calls */
        Map<Integer, List<Employee>> employeesByManager1 = employeeService.getEmployeesByManager();
        assertEquals(6, employeesByManager1.size());
    }
}