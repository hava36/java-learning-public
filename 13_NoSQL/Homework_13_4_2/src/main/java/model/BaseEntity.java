package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class BaseEntity {

  private String id;

  public BaseEntity(@NonNull String id) {
    this.id = id;
  }

}
