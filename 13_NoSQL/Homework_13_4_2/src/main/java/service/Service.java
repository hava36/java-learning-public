package service;

import java.util.Collection;

public interface Service<V, T> {

  void add(V v);
  void remove(V v);
  V find(T id);
  Collection<V> findAll();

}
