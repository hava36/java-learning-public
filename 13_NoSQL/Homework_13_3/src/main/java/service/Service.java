package service;

import dao.searcher.SearchHandler;
import java.util.List;

public interface Service<V> {

  void add(V v);

  void addAll(List<V> vs);

  List<V> readAll();

  List<V> read(SearchHandler<V> handler);

}
