import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    int index = 0;
    int total = 0;
    while (index < 3) {
      int spaceIndex = text.indexOf(' ');
      if (spaceIndex < 0) {
        break;
      }
      text = text.substring(spaceIndex, text.length()).trim();
      if (text.length() > 0 && Character.isDigit(text.charAt(0))) {
        int value = Integer.parseInt(text.substring(0 , text.indexOf(' ')));
        total += value;
        index++;
      }
    }
    System.out.println(total);
  }

}