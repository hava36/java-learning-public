package service;

import model.Inventory;
import model.Store;

public interface StoreService extends Service<Store, String> {

  void addInventory(Inventory inventory);

}
