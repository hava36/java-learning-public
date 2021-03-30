import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static ArrayList<String> tasks = new ArrayList<>();

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
            int limit = 2;
            if (request.matches("[A-Za-z]+[\\s]+[\\d]+[\\s]+[\\D\\d]+")) {
                limit = 3;
            }
            action(request.split("\\s", limit));
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
            case "EDIT":
                edit(params);
                break;
            case "DELETE":
                delete(params);
                break;
            default:
                System.out.println("Unknown operation!");
                break;
        }
    }

    private static void list() {
        for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {
            System.out.println(String.format("%d - %s", taskIndex, tasks.get(taskIndex)));
        }
    }

    private static void add(String[] params) {
        if (params.length == 2) {
            if (checkTaskName(params[1])) {
                tasks.add(params[1]);
                System.out.println("Adding was completed.");
            }
        } else if (params.length == 3) {
            try {
                if (checkTaskName(params[2])) {
                    tasks.add(Integer.parseInt(params[1]), params[2]);
                    System.out.println("Adding was completed.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Element with this index does not exists. Deletion was cancelled.");
            } catch (NumberFormatException e) {
                System.out.println("Error parsing index");
            }
        } else {
            System.out.println("Unknown opearator. Operation doesn't complete!");
        }
    }

    private static boolean checkTaskName(String taskName) {

        if (taskName.trim().isEmpty()) {
            System.out.println("Task is empty. Operation was cancelled!");
            return false;
        }
        return true;
    }

    private static void edit(String[] params) {
        if (params.length == 3) {
            try {
                if (checkTaskName(params[2])) {
                    tasks.set(Integer.parseInt(params[1]), params[2]);
                    System.out.println("Changing was completed.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Element with this index does not exists. Deletion was cancelled.");
            } catch (NumberFormatException e) {
                System.out.println("Error parsing index");
            }
        } else {
            System.out.println("Unknown opearator. Operation doesn't complete!");
        }
    }

    private static void delete(String[] params) {
        if (params.length == 2) {
            try {
                tasks.remove(Integer.parseInt(params[1]));
                System.out.println("Deletion was completed.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Element with this index does not exists. Deletion was cancelled.");
            } catch (NumberFormatException e) {
                System.out.println("Error parsing index");
            }
        } else {
            System.out.println("Unknown opearator. Operation doesn't complete!");
        }
    }

}
