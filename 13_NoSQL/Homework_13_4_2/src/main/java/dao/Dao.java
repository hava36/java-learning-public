package dao;

import java.util.Collection;

public interface Dao<V, T> {

  void add(V v);
  void remove(V v);
  V find(T id);
  Collection<V> findAll();

}
