package units.employees;

import units.Company;

public abstract class Employee {

    private int fixSalary;
    private int bonusPercentage;
    private int income;
    private Company company;

    public Employee(Company company, int salary) {
        this.fixSalary = salary;
        this.company = company;
    }

    public void setFixSalary(int fixSalary) {
        this.fixSalary = fixSalary;
    }

    public int getFixSalary() {
        return fixSalary;
    }

    public double getBonusPercentage() {
        return bonusPercentage;
    }

    public Company getCompany() {
        return company;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public double getIncome() {
        return income;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format("%d руб.", getMonthSalary());
    }

    public void setBonusPercentage(int bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public abstract int getMonthSalary();

}
