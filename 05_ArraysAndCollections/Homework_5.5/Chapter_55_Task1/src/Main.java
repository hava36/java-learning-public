import com.sun.source.tree.Tree;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

public class Main {

    private static final int COUNT_OF_CARS = 3000000;

    public static void main(String[] args) {

        List<String> carNumbers = generateCarNumbers();

        List<String> testList = new ArrayList<>(carNumbers);
        HashSet<String> testHashSet = new HashSet<>(carNumbers);
        TreeSet<String> testTreeSet = new TreeSet<>(carNumbers);

        String searchingString = testList.get(testList.size() - 1);

        long startTime = System.nanoTime();
        boolean isFound = testList.contains(searchingString);
        System.out.printf("Поиск перебором: %s, поиск занял %d нс\n",
                isFound ? "номер найден" : "номер не найден", System.nanoTime() - startTime);

        startTime = System.nanoTime();
        int index = Collections.binarySearch(testList, searchingString);
        System.out.printf("Бинарный поиск: %s, поиск занял %d нс\n",
                index < 0 ? "номер не найден" : "номер найден", System.nanoTime() - startTime);

        startTime = System.nanoTime();
        isFound = testHashSet.contains(searchingString);
        System.out.printf("Поиск в HashSet: %s, поиск занял %d нс\n",
                isFound ? "номер найден" : "номер не найден", System.nanoTime() - startTime);

        startTime = System.nanoTime();
        isFound = testTreeSet.contains(searchingString);
        System.out.printf("Поиск в TreeSet: %s, поиск занял %d нс\n",
                isFound ? "номер найден" : "номер не найден", System.nanoTime() - startTime);


    }


    private static List<String> generateCarNumbers() {
        ArrayList<String> carNumbers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < COUNT_OF_CARS; index++) {
            sb.setLength(0);
            sb.append(RandomStringUtils.randomAlphabetic(3).toUpperCase());
            sb.insert(1, RandomStringUtils.randomNumeric(3));
            String region = String.valueOf(RandomUtils.nextInt(1, 200));
            if (region.length() < 2) {
                sb.append("0");
            }
            sb.append(region);
            carNumbers.add(sb.toString());
        }
        Collections.sort(carNumbers);
        return carNumbers;
    }



}
