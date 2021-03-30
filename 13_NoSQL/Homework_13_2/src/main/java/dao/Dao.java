package dao;

import java.util.List;

public interface Dao<T, V> {

    void add(V v);
    void update(V v);
    void delete(V v);
    V get(T t);
    List<V> getAll();

}
