package command.impl;

import command.CommandHandler;
import model.Store;
import service.Service;

public class AddStoreCommand implements CommandHandler {

  private final Service<Store, String> storeService;

  public AddStoreCommand(Service<Store, String> storeService) {
    this.storeService = storeService;
  }

  @Override
  public void execute(String inputData) {

  }
}
