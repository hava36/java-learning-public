package service;

import java.util.List;

public interface Service<V, T> {

    void add(T t);
    T get(V v);
    List<T> getAll();
    void remove(V v);

}
