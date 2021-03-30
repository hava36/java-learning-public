import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      String regex = "(?<surname>[А-Яа-я\\-]+)[\\s]+(?<name>[А-Яа-я\\-]+)[\\s]+(?<middlename>[А-Яа-я\\-]+)";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(input.trim());
      if (matcher.matches()) {
        System.out.printf("Фамилия: %s%nИмя: %s%nОтчество: %s",
                matcher.group("surname"), matcher.group("name"), matcher.group("middlename"));
       } else {
        System.out.print("Введенная строка не является ФИО");
      }
    }
  }

}