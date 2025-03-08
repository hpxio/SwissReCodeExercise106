package io.code.assignment.util;

import io.code.assignment.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String DELIMITER = ",";

    public List<Employee> readFileContent(String path) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            /*skip header line */
            String line = br.readLine();

            /* read consecutive lines & prepare employee object */
            while ((line = br.readLine()) != null) {
                Employee e = buildEmployee(line.split(DELIMITER));
                employees.add(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    private Employee buildEmployee(String[] content) {
        String firstName = content[1].trim();
        String lastName = content[2].trim();

        Integer managerId = getManagerId(content);
        Integer id = Integer.parseInt(content[0].trim());
        Integer salary = Integer.parseInt(content[3].trim());

        return new Employee(id, firstName, lastName, salary, managerId);
    }

    private Integer getManagerId(String[] content) {
        if ((4 < content.length) && (!content[4].isBlank())) {
            return Integer.parseInt(content[4].trim());
        } else {
            return 0;
        }
    }
}
