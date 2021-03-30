package dao;

import java.util.List;

public interface Dao<V, K> {

    void add(K k);
    K get(V v);
    List<K> getAll();
    void remove(V v);

}
