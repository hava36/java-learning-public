package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RabinKarpExtended {
    private String text;
    private TreeMap<Integer, Integer> number2position;

    public RabinKarpExtended(String text) throws Exception {
        this.text = text;
        createIndex();
    }

    public List<Integer> search(String query) throws Exception {
        ArrayList<Integer> indices = new ArrayList<>();
        if (query.isEmpty()) return indices;
        List<Integer> queryIndexes = new ArrayList<>();
        for (char symbol : query.toCharArray()) {
            queryIndexes.add((int) symbol);
        }
        for (int mainIndex = 0; mainIndex < (number2position.size() - queryIndexes.size()); mainIndex++) {
            List<Integer> checkedList = new ArrayList<>();
            for (int subIndex = mainIndex; subIndex < mainIndex + queryIndexes.size(); subIndex++) {
                checkedList.add(number2position.get(subIndex));
            }
            if (queryIndexes.equals(checkedList)) {
                for (int subIndex = mainIndex; subIndex < mainIndex + queryIndexes.size(); subIndex++) {
                    indices.add(subIndex);
                }
            }
        }
        return indices;
    }

    private void createIndex() throws Exception {
        number2position = new TreeMap<>();
        char[] symbols = text.toCharArray();
        for (int charIndex = 0; charIndex < symbols.length; charIndex++) {
            number2position.put(charIndex, (int) symbols[charIndex]);
        }
        if (number2position.values().stream().collect(Collectors.toSet()).size() > 9) throw new Exception();
    }
}