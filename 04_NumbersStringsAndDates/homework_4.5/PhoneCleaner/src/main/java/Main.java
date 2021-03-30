import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            input = input.replaceAll("[^\\d]", "");
            if (input.matches("[7-8][\\d]{10}")) {
                System.out.println(input.replaceFirst("\\d", "7"));
            } else if (input.matches("[\\d]{10}")) {
                System.out.print('7');
                System.out.print(input);
            } else
                System.out.println("Неверный формат номера");
        }
    }
}


