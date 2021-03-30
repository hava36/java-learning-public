package command.impl;

import command.CommandHandler;
import model.Product;
import service.Service;

public class AddProductCommand implements CommandHandler {

  private final Service<Product, String> productService;

  public AddProductCommand(Service<Product, String> productService) {
    this.productService = productService;
  }

  @Override
  public void execute(String inputData) {

  }

}
