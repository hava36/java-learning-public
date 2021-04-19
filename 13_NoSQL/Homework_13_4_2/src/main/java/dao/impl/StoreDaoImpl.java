package dao.impl;

import com.mongodb.client.model.Filters;
import dao.StoreDao;
import dao.impl.aggregator.Aggregator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import model.Inventory;
import model.Store;
import org.bson.Document;
import utils.MongoUtils;

public class StoreDaoImpl implements StoreDao {

  @Override
  public void add(Store store) {
    MongoUtils.getStoreCollection().insertOne(store);
  }

  @Override
  public void remove(String title) {
    MongoUtils.getStoreCollection().findOneAndDelete(Filters.eq("title", title));
  }

  @Override
  public Store find(String title) {
    return MongoUtils.getStoreCollection().find(Filters.eq("title", title)).first();
  }

  @Override
  public Collection<Store> findAll() {
    Collection<Store> resultList = new ArrayList<>();
    Iterator<Store> iterator = MongoUtils.getStoreCollection().find().iterator();
    while (iterator.hasNext()) {
      resultList.add(iterator.next());
    }
    return resultList;
  }

  @Override
  public List<Document> aggregate(Aggregator aggregator) {
    return aggregator.aggregate();
  }

  @Override
  public void addInventory(Inventory inventory) {
    MongoUtils.getInventoryCollection().insertOne(inventory);
  }


}
