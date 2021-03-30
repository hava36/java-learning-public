import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static Map<String, String> contacts = new TreeMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("EXIT")) {
                break;
            }
            if (input.equalsIgnoreCase("LIST")) {
                printContacts();
            } else {
                addContact(input, scanner);
            }
        }
    }

    private static void addContact(String input, Scanner scanner) {
        if (input.isEmpty()) return;
        String number = "";
        String name = "";
        if (isPhoneNumber(input)) {
            number = input.replaceAll("[\\s\\-\\+\\(\\)]", "");
            while (name.isEmpty()) {
                System.out.println("Enter the name please");
                name = scanner.nextLine();
                if (number.equalsIgnoreCase("CANCEL")) {
                    return;
                }
            }
        } else {
            name = input;
            while (!isPhoneNumber(number)) {
                System.out.println("Enter the phone number");
                number = scanner.nextLine();
                if (number.equalsIgnoreCase("CANCEL")) {
                    return;
                }
                if (isPhoneNumber(number) && !number.isEmpty()) {
                    break;
                }
            }
        }

        contacts.put(name, number);
        System.out.println("phone number was added in contact list");

    }

    private static boolean isPhoneNumber(String input) {
        return input.matches("^\\+?[0-9\\-\\s\\(\\)]{1,}$");
    }

    private static void printContacts() {
        for (String name : contacts.keySet()
        ) {
            System.out.printf("%s - %s\n", name, contacts.get(name));
        }
    }

}
