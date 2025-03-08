package io.code.assignment.util;

import io.code.assignment.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    private FileUtil fileUtil;

    @BeforeEach
    void setUp() {
        fileUtil = new FileUtil();
    }

    @Test
    void testFileUtilValidFileStructure() throws IOException {
        List<Employee> employees = fileUtil.readFileContent("src/test/resources/test_input.csv");

        /* assert total records read are correct */
        assertEquals(16, employees.size());

        /* assert random user and their content */
        Employee employee1 = employees.stream()
                .filter(e -> e.getFirstName().equalsIgnoreCase("Olivia"))
                .findAny().get();

        assertEquals(204, employee1.getId());
        assertEquals("Olivia", employee1.getFirstName());
        assertEquals("Pink", employee1.getLastName());
        assertEquals(47000, employee1.getSalary());
        assertEquals(202, employee1.getManagerId());
    }

    @Test
    void testFileUtilWithNoFileContent() throws IOException {
        Path tempFile = Files.createTempFile("empty-file", ".csv");
        Files.write(tempFile, "".getBytes());

        List<Employee> employees = assertDoesNotThrow(() ->
                fileUtil.readFileContent(tempFile.toString()));
        assertTrue(employees.isEmpty());

        /* remove temporary file */
        Files.delete(tempFile);
    }

    @Test
    void testReadFileContent_FileNotFound() {
        String invalidPath = "non_existing_file.csv";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fileUtil.readFileContent(invalidPath);
        });
        assertInstanceOf(FileNotFoundException.class, exception.getCause());
    }
}
