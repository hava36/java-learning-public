package model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product implements Serializable {

  private String id;
  private String name;
  private Double price;

}
