package command.impl;

import command.CommandHandler;
import model.Product;
import model.Store;
import service.Service;

public class AddProductInStoreCommand implements CommandHandler {

  private final Service<Product, String> productService;
  private final Service<Store, String> storeService;

  public AddProductInStoreCommand(Service<Product, String> productService,
      Service<Store, String> storeService) {
    this.productService = productService;
    this.storeService = storeService;
  }

  @Override
  public void execute(String inputData) {

  }

}
