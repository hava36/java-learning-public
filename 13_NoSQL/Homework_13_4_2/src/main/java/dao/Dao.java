package dao;

import dao.impl.aggregator.Aggregator;
import java.util.Collection;
import java.util.List;
import org.bson.Document;

public interface Dao<V, T> {

  void add(V v);

  void remove(T t);

  V find(T t);

  Collection<V> findAll();

  List<Document> aggregate(Aggregator aggregator);

}
