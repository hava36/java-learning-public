import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            read(input);

        }
    }

    private static void read(String input) {

        int index = 1;
        String name = "";
        String surname = "";
        String middleName = "";
        String value = "";
        int spaceIndex = input.indexOf(' ') > 0 ? input.indexOf(' ') : input.length();
        while (index < 4) {
            value = input.substring(0, spaceIndex).trim();
            if (index == 1) {
                surname = value;
            } else if (index == 2) {
                name = value;
            } else if (index == 3) {
                middleName = value;
            }
            input = input.substring(spaceIndex, input.length()).trim();
            spaceIndex = input.indexOf(' ') > 0 ? input.indexOf(' ') : input.length();
            index++;
        }
        sendResult(name, surname, middleName, input);
    }

    private static void sendResult(String name, String surname, String middleName, String remain) {

        boolean mistake = false;
        String[] credentials = new String[]{name, surname, middleName};
        for (String value : credentials
        ) {
            for (int i = 0; i < value.length(); i++) {
                int ch = value.charAt(i);
                if ((ch < 1040 || ch > 1105) && ch != 45) {
                    mistake = true;
                    break;
                }
            }
        }
        if (name.isEmpty() || surname.isEmpty() || middleName.isEmpty() || !remain.isEmpty()) {
            mistake = true;
        }
        if (mistake) {
            System.out.print("Введенная строка не является ФИО");
        } else {
            System.out.printf(String.format("Фамилия: %s\nИмя: %s\nОтчество: %s\n", surname, name, middleName));
        }

    }

}

