import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) throws IOException, URISyntaxException {

    String text = "I was be able to do it! It's cool!";
    System.out.println(splitTextInToWords(text));

  }

  public static String splitTextInToWords(String text) {
    String replacedText = text.replaceAll("[\\W\\d&&[^\\’]]+", " ").trim();
    if (!replacedText.isEmpty()) {
      String[] words = replacedText.split("[\\s+]");
      return String.join(System.lineSeparator(), words);
    }
    return "";
  }

}