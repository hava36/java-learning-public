package command.impl;

import command.CommandHandler;
import exception.NoProductException;
import exception.NoStoreException;
import exception.ParseCommandException;
import java.util.UUID;
import model.Inventory;
import model.Product;
import model.Store;
import service.ProductService;
import service.StoreService;

public class AddProductInStoreCommand implements CommandHandler {

  private final ProductService productService;
  private final StoreService storeService;

  public AddProductInStoreCommand(ProductService productService,
      StoreService storeService) {
    this.productService = productService;
    this.storeService = storeService;
  }

  @Override
  public void execute(String inputData)
      throws ParseCommandException, NoProductException, NoStoreException {
    String[] commandParts = inputData.split("\\s+");
    try {
      Product product = productService.find(commandParts[1]);
      Store store = storeService.find(commandParts[2]);
      if (product == null) {
        throw new NoProductException(commandParts[1]);
      }
      if (store == null) {
        throw new NoStoreException(commandParts[2]);
      }
      Inventory inventory = new Inventory(UUID.randomUUID().toString(), store.getId(),
          product.getId());
      inventory.setId(UUID.randomUUID().toString());
      storeService.addInventory(inventory);
    } catch (ArrayIndexOutOfBoundsException exception) {
      throw new ParseCommandException(exception.getMessage());
    }

  }

}
