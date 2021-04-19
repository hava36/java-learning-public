package command.impl;

import command.CommandHandler;
import exception.ParseCommandException;
import java.util.UUID;
import model.Product;
import service.Service;

public class AddProductCommand implements CommandHandler {

  private final Service<Product, String> productService;

  public AddProductCommand(Service<Product, String> productService) {
    this.productService = productService;
  }

  @Override
  public void execute(String inputData) throws ParseCommandException {
    String[] commandParts = inputData.split("\\s+");
    try {
      Product product = new Product(UUID.randomUUID().toString(), commandParts[1],
          Double.parseDouble(commandParts[2]));
      productService.add(product);
    } catch (ArrayIndexOutOfBoundsException exception) {
      throw new ParseCommandException(exception.getMessage());
    } catch (NumberFormatException exception) {
      throw new ParseCommandException(exception.getMessage());
    }
  }

}
