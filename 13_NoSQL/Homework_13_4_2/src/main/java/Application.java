import controller.ConsoleController;
import controller.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

  public static void main(String[] args) {

    Controller<String> controller = new ConsoleController();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String inputData = null;
    try {
      while ((inputData = reader.readLine()) != null) {
        controller.execute(inputData);
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }

  }

}
