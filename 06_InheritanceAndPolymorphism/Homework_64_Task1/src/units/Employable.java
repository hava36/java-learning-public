package units;

import units.employees.Employee;

import java.util.List;

public interface Employable {

    void hire(Employee employee);
    void hireAll(List<Employee> employees);
    void fire(Employee employee);
    double getIncome();
    List<Employee> getTopSalaryStaff(int count);
    List<Employee> getLowestSalaryStaff(int count);

}
