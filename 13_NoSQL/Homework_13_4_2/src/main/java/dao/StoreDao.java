package dao;

import model.Inventory;
import model.Store;

public interface StoreDao extends Dao<Store, String> {

  void addInventory(Inventory inventory);

}
