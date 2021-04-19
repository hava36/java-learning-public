package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistrationGUI {

  private JTextField textFieldFirstName;
  private JPanel mainPanel;
  private JTextField textFieldMiddleName;
  private JTextField textFieldLastName;
  private JButton collapseButton;
  private JLabel labelFirstName;
  private JLabel labelMiddleName;
  private JLabel labelLastName;
  private JPanel credentialsPanel;
  private JTextField textFieldFullName;
  private JLabel labelFullName;
  private JPanel commonPanel;
  private JButton expandButton;

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public RegistrationGUI() {

    collapseButton.addActionListener(
        new CollapseActionListener(Arrays.asList(textFieldFirstName, textFieldLastName)));

    expandButton.addActionListener(new ExpandActionListener());

    commonPanel.setVisible(false);

  }

  private class ExpandActionListener implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      String[] credentials = textFieldFullName.getText().split("\\s+");
      if (credentials.length < 2) {
        JOptionPane.showMessageDialog(mainPanel, "Wrong full name");
        return;
      } else if (credentials.length == 2) {
        textFieldFirstName.setText(credentials[0]);
        textFieldLastName.setText(credentials[1]);
        textFieldMiddleName.setText("");
      } else if (credentials.length ==3 ) {
        textFieldFirstName.setText(credentials[0]);
        textFieldMiddleName.setText(credentials[1]);
        textFieldLastName.setText(credentials[2]);
      }
      credentialsPanel.setVisible(true);
      commonPanel.setVisible(false);
    }
  }

  private class CollapseActionListener implements ActionListener {

    private List<JTextField> checkingTextFields;

    public CollapseActionListener(List<JTextField> checkingTextFields) {
      this.checkingTextFields = checkingTextFields;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      StringBuilder errors = new StringBuilder();
      for (JTextField textField : checkingTextFields
      ) {
        if (textField.getText().isEmpty()) {
          if (errors.length() > 0) {
            errors.append('\n');
          }
          errors.append(String.format("The field \"%s\" is empty", textField.getName()));
        }
      }
      if (errors.length() > 0) {
        JOptionPane.showMessageDialog(mainPanel, errors.toString());
      } else {
        credentialsPanel.setVisible(false);
        commonPanel.setVisible(true);
        textFieldFullName.setText(String
            .format("%s %s %s", textFieldFirstName.getText(), textFieldMiddleName.getText(),
                textFieldLastName.getText()));
      }
    }
  }

}
