package dao.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dao.ProductDao;
import dao.impl.aggregator.Aggregator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import model.Product;
import org.bson.Document;
import utils.MongoUtils;

public class ProductDaoImpl implements ProductDao {

  private static final String PRODUCT_COLLECTION_NAME = "product";
  private static final MongoCollection<Product> collection = MongoUtils.getDatabase()
      .getCollection(PRODUCT_COLLECTION_NAME, Product.class);

  @Override
  public void add(Product product) {
    collection.insertOne(product);
  }

  @Override
  public void remove(String name) {
    collection.findOneAndDelete(Filters.eq("name", name));
  }

  @Override
  public Product find(String name) {
    return collection.find(Filters.eq("name", name)).first();
  }

  @Override
  public Collection<Product> findAll() {
    Collection<Product> resultList = new ArrayList<>();
    Iterator<Product> iterator = collection.find().iterator();
    while (iterator.hasNext()) {
      resultList.add(iterator.next());
    }
    return resultList;
  }

  @Override
  public List<Document> aggregate(Aggregator aggregator) {
    return aggregator.aggregate();
  }


}
