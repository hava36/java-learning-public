package command.impl;

import command.CommandHandler;
import exception.ParseCommandException;
import java.util.UUID;
import model.Store;
import service.StoreService;

public class AddStoreCommand implements CommandHandler {

  private final StoreService storeService;

  public AddStoreCommand(StoreService storeService) {
    this.storeService = storeService;
  }

  @Override
  public void execute(String inputData) throws ParseCommandException {
    String[] commandParts = inputData.split("\\s+");
    try {
      Store store = new Store(UUID.randomUUID().toString(), commandParts[1]);
      storeService.add(store);
    } catch (ArrayIndexOutOfBoundsException exception) {
      throw new ParseCommandException("");
    }
  }
}
