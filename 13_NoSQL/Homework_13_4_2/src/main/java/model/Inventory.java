package model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Inventory extends BaseEntity implements Serializable {

  private final String storeId;
  private final String productId;

  public Inventory(@NonNull String id, @NonNull String storeId, @NonNull String productId) {
    super(id);
    this.storeId = storeId;
    this.productId = productId;
  }
}
