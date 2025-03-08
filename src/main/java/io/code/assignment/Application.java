package io.code.assignment;

import io.code.assignment.model.Employee;
import io.code.assignment.util.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {

        /* read file content, get all employee in one place */
        FileUtil fileUtil = new FileUtil();
        List<Employee> employees = fileUtil.readFileContent("src/main/resources/input.csv");


        /* get all employees for each manager */
        Map<Integer, List<Employee>> managers = new HashMap<>();
        employees.forEach(e -> {
            Integer managerId = e.getManagerId();
            if (managerId != 0) {
                if (!managers.containsKey(managerId)) {
                    managers.put(managerId, new ArrayList<>());
                }
                managers.get(managerId).add(e);
            }
        });


        /* pre-calculate depth and populate each employee */
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employees.forEach(e -> employeeMap.put(e.getId(), e));

        employees.forEach(e -> {
            int depth = 0;
            Integer managerId = e.getManagerId();
            while (managerId != 0) {
                depth++;
                Employee manager = employeeMap.get(managerId);
                if (manager == null) break;
                managerId = manager.getManagerId();
            }
            e.setDepth(depth);
        });


        managers.forEach((mgrId, eList) -> {
            double avg = eList.stream()
                    .mapToInt(Employee::getSalary)
                    .average()
                    .orElse(0.0);

            double avg20P = avg * 1.2;
            double avg50P = avg * 1.5;

            Employee mgr = employeeMap.get(mgrId);
            int mgrSal = mgr.getSalary();

            if (mgrSal > avg20P && mgrSal <= avg50P) {
                System.out.println("Manager: " + mgr.getFirstName() + " (ID: " + mgrId + ") - OK");
            } else if (mgrSal < avg20P) {
                System.out.println("Manager: " + mgr.getFirstName() + " (ID: " + mgrId + ") - Earning Less");
                System.out.println("Shortfall: " + (avg20P - mgrSal));
            } else if (mgrSal > avg50P) {
                System.out.println("Manager: " + mgr.getFirstName() + " (ID: " + mgrId + ") - Earning More");
                System.out.println("Excess: " + (mgrSal - avg50P));
            }
        });
    }
}
