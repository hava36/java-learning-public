package units.employees;

import units.Company;

public class Operator extends Employee {

    public Operator(Company company, int salary) {
        super(company, salary);
    }

    @Override
    public int getMonthSalary() {
        return getFixSalary();
    }

}
