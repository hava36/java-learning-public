package dao;

import dao.searcher.SearchHandler;
import java.util.List;

public interface Dao<V> {

  void add(V v);

  void addAll(List<V> vs);

  List<V> readAll();

  List<V> read(SearchHandler<V> handler);

}
