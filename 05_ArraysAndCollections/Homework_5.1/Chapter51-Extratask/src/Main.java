public class Main {

    private static final int COUNT_OF_ARRAY = 7;

    public static void main(String[] args) {
        int[][] array = new int[COUNT_OF_ARRAY][COUNT_OF_ARRAY];
        for (int y = 0; y < array.length; y++) {
            int[] row = array[y];
            for (int x = 0; x < row.length; x++) {
                if ((x == y) || (row.length - x - 1 == y)) {
                    row[x] = 1;
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
