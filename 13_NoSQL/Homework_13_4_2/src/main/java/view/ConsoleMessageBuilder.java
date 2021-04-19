package view;

import java.util.ResourceBundle;

public class ConsoleMessageBuilder implements MessageBuilder {

  private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(
      "console_messages/messages");

  @Override
  public void printWelcomeMessage() {
    System.out.println(resourceBundle.getString("title"));
  }

  @Override
  public String prepareTextParseActionError(String action) {
    return String.format(resourceBundle.getString("parse_action_error"), action);
  }

}
