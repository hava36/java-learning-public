package collections;

import exceptions.ParseMovementException;
import model.Company;
import model.Movement;
import parsers.MovementParser;

import java.util.*;


public class MovementBuilder {

    private ResourceBundle bundle = ResourceBundle.getBundle("totals");
    private HashMap<Company, List<Movement>> movements;

    public MovementBuilder() {
        this.movements = new HashMap<>();
    }

    public void addMovement(Movement movement) {
        List<Movement> movementList = new ArrayList<>();
        if (movements.containsKey(movement.getCompany())) {
            movementList = movements.get(movement.getCompany());
        }
        movementList.add(movement);
        movements.put(movement.getCompany(), movementList);
    }

    public void addMovement(String sourceData) throws ParseMovementException, NumberFormatException {
        addMovement(MovementParser.parseCSV(sourceData));
    }

    public void showTotalResult() {
        List<Movement> allItems = new ArrayList<>();
        for (List<Movement> value : movements.values()
             ) {
            allItems.addAll(value);
        }
        Double totalIncome = allItems.stream().mapToDouble(Movement::getIncome).sum();
        Double totalExpense = allItems.stream().mapToDouble(Movement::getExpense).sum();
        System.out.printf(bundle.getString("total"), totalIncome, totalExpense);
        System.out.print("\n\n");
    }

    public void showResultForCompanies() {

        System.out.println(bundle.getString("details_title"));
        movements.forEach(((company, movementList) -> {
            System.out.printf(bundle.getString("details_row"), company,
                    movementList.stream().mapToDouble(Movement::getExpense).sum());
            System.out.print('\n');
        }));

    }

}
