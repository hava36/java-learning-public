package service.impl;

import dao.StoreDao;
import dao.impl.StoreDaoImpl;
import dao.impl.aggregator.Aggregator;
import java.util.Collection;
import java.util.List;
import model.Inventory;
import model.Store;
import org.bson.Document;
import service.StoreService;

public class StoreServiceImpl implements StoreService {

  private final StoreDao storeDao;

  public StoreServiceImpl() {
    this.storeDao = new StoreDaoImpl();
  }

  @Override
  public void add(Store store) {
    storeDao.add(store);
  }

  @Override
  public void remove(String id) {
    storeDao.remove(id);
  }

  @Override
  public Store find(String id) {
    return storeDao.find(id);
  }

  @Override
  public Collection<Store> findAll() {
    return storeDao.findAll();
  }

  @Override
  public List<Document> aggregate(Aggregator aggregator) {
    return storeDao.aggregate(aggregator);
  }

  @Override
  public void addInventory(Inventory inventory) {
    storeDao.addInventory(inventory);
  }

}
