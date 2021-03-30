import factory.RandomUnitFactory;
import units.Company;
import units.employees.Employee;

import java.io.IOException;
import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {

        Company company = new Company();
        RandomUnitFactory randomUnitFactory = new RandomUnitFactory(company);
        randomUnitFactory.createUnits();
        randomUnitFactory.printUnits();
        randomUnitFactory.removeUnits(company.getEmployees().size() / 2);
        randomUnitFactory.printUnits();

    }

}
