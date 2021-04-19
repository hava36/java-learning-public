package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import dao.impl.aggregator.Aggregator;
import java.util.Collection;
import java.util.List;
import model.Product;
import org.bson.Document;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao;

  public ProductServiceImpl() {
    this.productDao = new ProductDaoImpl();
  }

  @Override
  public void add(Product product) {
    productDao.add(product);
  }

  @Override
  public void remove(String id) {
    productDao.remove(id);
  }

  @Override
  public Product find(String id) {
    return productDao.find(id);
  }

  @Override
  public Collection<Product> findAll() {
    return productDao.findAll();
  }

  @Override
  public List<Document> aggregate(Aggregator aggregator) {
    return aggregator.aggregate();
  }

}
