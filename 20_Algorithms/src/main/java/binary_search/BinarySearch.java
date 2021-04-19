package binary_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    private final List<String> list;

    public BinarySearch(List<String> list) {
        Collections.sort(list);
        this.list = list;
    }

    public int search(String query) {
        return search(query, 0, list.size() - 1);
    }

    private int search(String query, int from, int to) {
        if (list.size() == 0) return -1;
        int index = from + (to - from) / 2;
        String value = list.get(index);
        int compareResult = value.compareTo(query);
        if (from == to) {
            return compareResult == 0 ? index : -1;
        } else {
            if (compareResult > 0) {
                return search(query, from, Math.max(--index, from));
            } else if (compareResult < 0) {
                return search(query, Math.min(++index, to), to);
            } else {
                return index;
            }
        }
    }
}
