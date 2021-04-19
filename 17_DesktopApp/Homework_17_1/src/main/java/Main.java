import javax.swing.JFrame;
import javax.swing.WindowConstants;
import view.RegistrationGUI;

public class Main {

  public static void main(String[] args) {

    JFrame frame = new JFrame();
    frame.add(new RegistrationGUI().getMainPanel());
    frame.setSize(600, 400);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

  }

}
