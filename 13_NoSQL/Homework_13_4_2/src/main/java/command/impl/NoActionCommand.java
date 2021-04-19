package command.impl;

import command.CommandHandler;
import exception.EmptyCommandStringException;

public class NoActionCommand implements CommandHandler {

  @Override
  public void execute(String inputData) throws EmptyCommandStringException {
    throw new EmptyCommandStringException();
  }
}
