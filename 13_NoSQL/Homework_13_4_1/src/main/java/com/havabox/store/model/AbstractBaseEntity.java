package com.havabox.store.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractBaseEntity implements Serializable {

  private String id;

}
