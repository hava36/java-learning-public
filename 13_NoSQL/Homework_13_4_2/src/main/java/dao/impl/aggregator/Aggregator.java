package dao.impl.aggregator;

import java.util.List;

public interface Aggregator<V> {

  List<V> aggregate();

}
