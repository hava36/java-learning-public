package dao.searcher;

import java.util.List;

public interface SearchHandler<V> {

  List<V> find();

}
