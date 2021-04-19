package model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Product extends BaseEntity implements Serializable {

  private final String name;
  private final Double price;

  public Product(@NonNull String id, @NonNull String name, @NonNull Double price) {
    super(id);
    this.name = name;
    this.price = price;
  }
}
