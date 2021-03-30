package com.havabox.store.service;

import java.util.Collection;

public interface Service<V, I> {

  void add(V v);

  void delete(I id);

  V find(I id);

  Collection<V> findAll();

}
