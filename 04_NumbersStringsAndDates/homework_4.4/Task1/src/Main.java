public class Main {

    public static void main(String[] args) {
        for (int i = 65; i < 123; i++) {
            if (i > 90 && i < 97) {
                continue;
            }
            System.out.printf("%s - %s\n", String.valueOf((char) i), i);
        }
    }
}
