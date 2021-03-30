import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int COUNT_IN_CONTAINER = 27;
    private static final int COUNT_IN_CAR = 12;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countOfBoxes = Integer.parseInt(reader.readLine());
        int countOfContainers = countOfBoxes / COUNT_IN_CONTAINER + (countOfBoxes % COUNT_IN_CONTAINER == 0 ? 0 : 1);
        int containerIndex = countOfContainers;
        int countOfCars = countOfContainers / COUNT_IN_CAR + + (countOfContainers % COUNT_IN_CAR == 0 ? 0 : 1);
        for (int i = 1; i <= countOfCars; i++
             ) {
            System.out.println(String.format("Грузовик %s", i));
            for (int j = 1; j <= Math.min(containerIndex, COUNT_IN_CAR); j++
                 ) {
                System.out.println(String.format("\tКонтейнер %s", j));
                for (int k = 1; k <= Math.min(countOfBoxes, COUNT_IN_CONTAINER); k++) {
                    System.out.println(String.format("\t\tЯщик: %s", k));
                }
                countOfBoxes -= COUNT_IN_CONTAINER;
            }
            containerIndex -= COUNT_IN_CAR;
        }
        System.out.println("Необходимо:");
        System.out.println(String.format("грузовиков %s шт.", countOfCars));
        System.out.println(String.format("контейнеров  %s шт.", countOfContainers));
    }

}
