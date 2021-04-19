package model;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Store extends BaseEntity implements Serializable {

  private final String title;

  @ToString.Exclude
  private List<Inventory> inventoryList;

  public Store(@NonNull String id, @NonNull String title) {
    super(id);
    this.title = title;
  }

  public Store(@NonNull String id, @NonNull String title, @NonNull List<Inventory> inventoryList) {
    super(id);
    this.title = title;
    this.inventoryList = inventoryList;
  }

}
