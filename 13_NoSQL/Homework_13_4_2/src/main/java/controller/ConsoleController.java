package controller;

import command.CommandHandler;
import command.impl.AddProductCommand;
import command.impl.AddProductInStoreCommand;
import command.impl.AddStoreCommand;
import command.impl.NoActionCommand;
import java.util.HashMap;
import java.util.Map;
import model.Product;
import model.Store;
import model.enums.Action;
import service.Service;
import service.impl.ProductService;
import service.impl.StoreService;

public class ConsoleController implements Controller<String> {

  private final Service<Product, String> productService;
  private final Service<Store, String> storeService;

  private final Map<Action, CommandHandler> commands;
  private final CommandHandler defaultCommand;

  public ConsoleController() {

    this.productService = new ProductService();
    this.storeService = new StoreService();

    commands = new HashMap<>();
    commands.put(Action.ADD_STORE, new AddStoreCommand(storeService));
    commands.put(Action.ADD_PRODUCT, new AddProductCommand(productService));
    commands.put(Action.ADD_PRODUCT_IN_STORE,
        new AddProductInStoreCommand(productService, storeService));
    defaultCommand = new NoActionCommand();

  }

  @Override
  public void execute(String inputData) {

    Action action = parseAction(inputData);
    CommandHandler command = commands.getOrDefault(action, defaultCommand);
    command.execute(inputData);

  }

  private Action parseAction(String inputData) {
    return null;
  }

}
