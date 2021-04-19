package bubble_sort;

import abstract_class.AbstractSortArrayClass;

public class BubbleSortTest extends AbstractSortArrayClass {

    @Override
    public void sortArray(int[] array) {
        BubbleSort.sort(array);
    }

}
