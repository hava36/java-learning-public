package model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Store implements Serializable {

  private String id;
  private String title;

}
