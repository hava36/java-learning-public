import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static Set<String> emails = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String request = null;
        while (scanner.hasNext()) {
            request = scanner.nextLine();
            if (request.isEmpty()) continue;
            if (request.toUpperCase().equals("EXIT")) {
                System.out.println("Good bye!");
                break;
            }
            action(request.split("\\s", 2));
        }
    }

    private static void action(String[] params) {
        switch (params[0].toUpperCase()) {
            case "LIST":
                list();
                break;
            case "ADD":
                add(params);
                break;
            default:
                System.out.println("Unknown operation!");
                break;
        }
    }

    private static void list() {
        for (String email : emails
        ) {
            System.out.println(email);
        }
    }

    private static void add(String[] params) {
        try {
            String email = params[1];
            if (email.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")) {
                emails.add(params[1]);
                System.out.println("Adding was completed.");
            } else {
                System.out.println("This string is not e-mail address. Operation was cancelled.");
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Element with this index does not exists. Adding was cancelled.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing index");
        }
    }

}
