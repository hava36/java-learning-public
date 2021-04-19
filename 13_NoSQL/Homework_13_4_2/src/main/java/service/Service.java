package service;

import dao.impl.aggregator.Aggregator;
import java.util.Collection;
import java.util.List;
import org.bson.Document;

public interface Service<V, T> {

  void add(V v);

  void remove(T r);

  V find(T id);

  Collection<V> findAll();

  List<Document> aggregate(Aggregator aggregator);

}
