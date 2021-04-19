package model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Action {

  ADD_STORE, ADD_PRODUCT, DISPLAY_PRODUCT, PRODUCT_STATISTICS;

  public static final Map<String, Action> RATIO;

  static {
    RATIO = new HashMap<>();
    RATIO.put("ДОБАВИТЬ_МАГАЗИН", ADD_STORE);
    RATIO.put("ДОБАВИТЬ_ТОВАР", ADD_PRODUCT);
    RATIO.put("ВЫСТАВИТЬ_ТОВАР", DISPLAY_PRODUCT);
    RATIO.put("СТАТИСТИКА_ТОВАРОВ", PRODUCT_STATISTICS);
  }

}
