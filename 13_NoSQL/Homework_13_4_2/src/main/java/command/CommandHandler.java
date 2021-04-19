package command;

import exception.EmptyCommandStringException;
import exception.NoProductException;
import exception.NoStoreException;
import exception.ParseCommandException;

public interface CommandHandler {

  void execute(String inputData)
      throws ParseCommandException, EmptyCommandStringException, NoProductException, NoStoreException;

}
