package controller;

import command.CommandHandler;
import command.impl.AddProductCommand;
import command.impl.AddProductInStoreCommand;
import command.impl.AddStoreCommand;
import command.impl.NoActionCommand;
import command.impl.ShowProductStatisticCommand;
import exception.EmptyCommandStringException;
import exception.NoProductException;
import exception.NoStoreException;
import exception.ParseActionException;
import exception.ParseCommandException;
import java.util.HashMap;
import java.util.Map;
import model.enums.Action;
import service.ProductService;
import service.StoreService;
import service.impl.ProductServiceImpl;
import service.impl.StoreServiceImpl;
import view.ConsoleMessageBuilder;
import view.MessageBuilder;

public class ConsoleController implements Controller<String> {

  private final Map<Action, CommandHandler> commands;
  private final CommandHandler defaultCommand;
  private final MessageBuilder messageBuilder;
  private final ProductService productService;
  private final StoreService storeService;

  public ConsoleController() {
    this.productService = new ProductServiceImpl();
    this.storeService = new StoreServiceImpl();
    this.messageBuilder = new ConsoleMessageBuilder();

    commands = new HashMap<>();
    commands.put(Action.ADD_STORE, new AddStoreCommand(storeService));
    commands.put(Action.ADD_PRODUCT, new AddProductCommand(productService));
    commands.put(Action.DISPLAY_PRODUCT,
        new AddProductInStoreCommand(productService, storeService));
    commands.put(Action.PRODUCT_STATISTICS,
        new ShowProductStatisticCommand(storeService, messageBuilder));

    defaultCommand = new NoActionCommand();
    messageBuilder.printWelcomeMessage();
  }

  @Override
  public void execute(String inputData) {
    try {
      Action action = parseAction(inputData);
      CommandHandler command = commands.getOrDefault(action, defaultCommand);
      command.execute(inputData);
    } catch (ParseActionException | EmptyCommandStringException | ParseCommandException |
        NoProductException | NoStoreException e) {
      e.printStackTrace();
    }
  }

  private Action parseAction(String inputData)
      throws EmptyCommandStringException, ParseActionException {
    String[] parts = inputData.split("\\s");
    if (parts.length == 0) {
      throw new EmptyCommandStringException();
    }
    String action = parts[0];
    if (!Action.RATIO.containsKey(action)) {
      throw new ParseActionException(messageBuilder.prepareTextParseActionError(action));
    }
    return Action.RATIO.get(action);
  }

}
