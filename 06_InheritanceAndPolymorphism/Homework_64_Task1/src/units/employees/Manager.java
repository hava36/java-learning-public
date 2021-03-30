package units.employees;

import units.Company;

public class Manager extends Employee {

    public Manager(Company company, int salary) {
        super(company, salary);
        setBonusPercentage(5);
        setIncome(115000 + (int) (Math.random() * 25000));
    }


    @Override
    public int getMonthSalary() {
        return getFixSalary() + (int) (getIncome() * getBonusPercentage() / 100);
    }

}
