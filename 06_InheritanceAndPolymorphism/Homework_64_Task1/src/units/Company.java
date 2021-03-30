package units;

import comparators.LowComparator;
import comparators.TopComparator;
import units.employees.Employee;

import java.util.ArrayList;
import java.util.List;

public class Company implements Employable {

    private List<Employee> employees;

    public Company() {
        this.employees = new ArrayList<>();
    }

    @Override
    public void hire(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void hireAll(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    @Override
    public void fire(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
        }
    }

    @Override
    public List<Employee> getTopSalaryStaff(int count) {
        List topEmployeeList = new ArrayList(employees);
        topEmployeeList.sort(new TopComparator());
        return topEmployeeList.subList(0, Math.min(count, topEmployeeList.size()));
    }

    @Override
    public List<Employee> getLowestSalaryStaff(int count) {
        List lowEmployeeList = new ArrayList(employees);
        lowEmployeeList.sort(new LowComparator());
        return lowEmployeeList.subList(0, Math.min(count, lowEmployeeList.size()));
    }

    public double getIncome() {
        double income = 0d;
        for (Employee employee : employees
             ) {
           income += employee.getIncome();
        }
        return income;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
