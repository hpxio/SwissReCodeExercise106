package io.code.assignment.service;

import io.code.assignment.model.Employee;
import io.code.assignment.model.Hierarchy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryServiceImpl implements SalaryService {

    private static final double MIN_PERCENT = 1.2;
    private static final double MAX_PERCENT = 1.5;

    private final Map<Employee, Integer> managerEarningOk = new HashMap<>();
    private final Map<Employee, Integer> managerEarningLess = new HashMap<>();
    private final Map<Employee, Integer> managerEarningMore = new HashMap<>();

    public SalaryServiceImpl(Hierarchy hierarchy) {
        calculateDeviation(hierarchy);
    }

    @Override
    public Map<Employee, Integer> getManagersEarningOk() {
        return this.managerEarningOk;
    }

    @Override
    public Map<Employee, Integer> getManagersEarningLess() {
        return this.managerEarningLess;
    }

    @Override
    public Map<Employee, Integer> getManagersEarningMore() {
        return this.managerEarningMore;
    }

    private void calculateDeviation(Hierarchy hierarchy) {
        Map<Integer, Employee> employees = hierarchy.getEmployees();
        Map<Integer, List<Employee>> managers = hierarchy.getManagers();

        managers.forEach((mgrId, empList) -> {
            double avg = empList.stream()
                    .mapToInt(Employee::getSalary)
                    .average()
                    .orElse(0.0);

            double avgMinLimit = avg * MIN_PERCENT;
            double avgMaxLimit = avg * MAX_PERCENT;

            Employee mgr = employees.get(mgrId);
            int mgrSal = mgr.getSalary();

            if (mgrSal > avgMinLimit && mgrSal <= avgMaxLimit) {
                this.managerEarningOk.put(mgr, mgrSal);
            } else if (mgrSal < avgMinLimit) {
                int deviation = (int) Math.ceil(avgMinLimit - mgrSal);
                this.managerEarningLess.put(mgr, deviation);
            } else if (mgrSal > avgMaxLimit) {
                int deviation = (int) Math.ceil(mgrSal - avgMaxLimit);
                this.managerEarningMore.put(mgr, deviation);
            }
        });
    }
}
