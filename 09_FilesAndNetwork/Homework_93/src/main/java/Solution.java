import collections.MovementBuilder;
import exceptions.ParseMovementException;
import java.io.*;

public class Solution {

    private static final String MOVEMENTS_FILENAME = "movementList.csv";

    public static void main(String[] args) {
        MovementBuilder movementBuilder = new MovementBuilder();
        try {
            File file = new File(Solution.class.getClassLoader().getResource(MOVEMENTS_FILENAME).getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            for (; ; ) {
                String line = reader.readLine();
                if (line == null) break;
                movementBuilder.addMovement(line);
            }
        } catch (IOException | ParseMovementException | NumberFormatException e) {
            e.printStackTrace();
        }
        movementBuilder.showTotalResult();
        movementBuilder.showResultForCompanies();
    }

}
