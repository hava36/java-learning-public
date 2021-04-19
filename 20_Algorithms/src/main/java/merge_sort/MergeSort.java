package merge_sort;

public class MergeSort {
    public static void mergeSort(int[] array) {
        int n = array.length;
        if (n < 2) {
            return;
        }
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int mainIndex = 0;
        while (mainIndex < array.length) {
            if (leftIndex == left.length) array[mainIndex++] = right[rightIndex++];
            else if (rightIndex == right.length) array[mainIndex++] = left[leftIndex++];
            else if (left[leftIndex] < right[rightIndex]) {
                array[mainIndex++] = left[leftIndex++];
            } else {
                array[mainIndex++] = right[rightIndex++];
            }
        }

    }
}
