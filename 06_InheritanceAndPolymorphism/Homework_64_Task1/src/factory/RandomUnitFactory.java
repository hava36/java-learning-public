package factory;

import units.Company;
import units.employees.Employee;
import units.employees.Manager;
import units.employees.Operator;
import units.employees.TopManager;

import java.util.ArrayList;
import java.util.List;


public class RandomUnitFactory {

    private Company company;

    public RandomUnitFactory(Company company) {
        this.company = company;
    }

    public void createUnits() {
        createOperators();
        createManagers();
        createTopManagers();
    }

    public void removeUnits(int count) {
        while (count >= 0) {
            int index = (int) (count * Math.random());
            company.fire(company.getEmployees().get(index));
            count--;
        }
    }

    private void createOperators() {
        for (int index = 0; index < 180; index++) {
            Operator operator = new Operator(company, (int) (80000 + Math.random() * 30000));
            company.hire(operator);
        }
    }

    private void createManagers() {
        for (int index = 0; index < 80; index++) {
            Manager manager = new Manager(company, (int) (140000 + Math.random() * 30000));
            company.hire(manager);
        }
    }

    private void createTopManagers() {
        for (int index = 0; index < 10; index++) {
            TopManager topManager = new TopManager(company, (int) (220000 + Math.random() * 50000));
            company.hire(topManager);
        }
    }

    public void printUnits() {
        System.out.println("Самые высокие заработные платы: ");
        for (Employee employee : getCompany().getTopSalaryStaff(15)) {
            System.out.println(employee);
        }
        System.out.println();

        System.out.println("Самые низкие заработные платы: ");
        for (Employee employee : getCompany().getLowestSalaryStaff(30)) {
            System.out.println(employee);
        }
        System.out.println();
    }

    public Company getCompany() {
        return company;
    }



}
