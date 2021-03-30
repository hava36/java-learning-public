package units.employees;

import units.Company;

public class TopManager extends Employee {

    private static final double INCOME_LIMIT = 10000000d;

    public TopManager(Company company, int salary) {
        super(company, salary);
        setBonusPercentage(150);
    }

    @Override
    public int getMonthSalary() {
        return getFixSalary() + (int) (getFixSalary() * getBonusPercentage() / 100);
    }

    @Override
    public double getBonusPercentage() {
        if (getCompany().getIncome() > INCOME_LIMIT) {
            return super.getBonusPercentage();
        }
        return 0d;
    }
}
