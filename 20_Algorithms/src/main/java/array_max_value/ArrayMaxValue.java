package array_max_value;

public class ArrayMaxValue {
    public static int getMaxValue(int[] values) throws Exception {
        if (values == null || values.length == 0) throw new Exception("Invalid values");
        int maxValue = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(getMaxValue(new int[]{-1, -2, -3}));

    }

}