package comparators;

import units.employees.Employee;

import java.util.Comparator;

public class TopComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        if (o2.getMonthSalary() > o1.getMonthSalary()) {
            return 1;
        } else if (o2.getMonthSalary() < o1.getMonthSalary()) {
            return -1;
        }
        return 0;
    }

}
