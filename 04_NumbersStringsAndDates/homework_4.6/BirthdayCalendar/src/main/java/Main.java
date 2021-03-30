import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        int day = 31;
        int month = 12;
        int year = 1990;
        System.out.println(collectBirthdays(year, month, day));
    }

    public static String collectBirthdays(int year, int month, int day) {
        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy - E", Locale.ENGLISH);
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (today.isAfter(birthday)) {
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(String.format("%d - %s", index, formatter.format(birthday)));
            birthday = birthday.plusYears(1);
            index++;
        }
        return sb.toString();
    }
}
