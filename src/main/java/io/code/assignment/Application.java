package io.code.assignment;

import io.code.assignment.model.Employee;
import io.code.assignment.model.Hierarchy;
import io.code.assignment.service.EmployeeService;
import io.code.assignment.service.EmployeeServiceImpl;
import io.code.assignment.service.SalaryService;
import io.code.assignment.service.SalaryServiceImpl;
import io.code.assignment.util.FileUtil;

import java.util.List;
import java.util.Map;

public class Application {

    private static final String FILE_PATH = "src/main/resources/input.csv";

    public static void main(String[] args) {

        List<Employee> dataFromFile =
                new FileUtil().readFileContent(FILE_PATH);

        /* prepare employee details from input file */
        EmployeeService employeeService =
                new EmployeeServiceImpl(dataFromFile);

        /* prepare consolidated data Hierarchy for processing */
        Map<Integer, Employee> allEmployees = employeeService.getAllEmployees();
        Map<Integer, List<Employee>> employeesByManager = employeeService.getEmployeesByManager();
        Hierarchy hierarchy = new Hierarchy(allEmployees, employeesByManager);


        /* print final results */
        printSalaryCalculationResult(hierarchy);
        printDepthCalculationResult(allEmployees);
    }

    private static void printDepthCalculationResult(Map<Integer, Employee> allEmployees) {
        System.out.println("Employess that have long reporting line:");
        allEmployees.values().stream().filter(v -> v.getDepth() > 4)
                .toList().forEach(e ->
                        System.out.println(e.getFirstName() + " by " + e.getDepth()));
    }

    private static void printSalaryCalculationResult(Hierarchy hierarchy) {
        /* initiate salary-calculations & print results */
        SalaryService salaryService =
                new SalaryServiceImpl(hierarchy);

        System.out.println("Manager that are earning less:");
        salaryService.getManagersEarningLess().forEach((key, value) ->
                System.out.println(key.getFirstName() + " by " + value));

        System.out.println("Manager that are earning more:");
        salaryService.getManagersEarningMore().forEach((key, value) ->
                System.out.println(key.getFirstName() + " by " + value));
    }
}
