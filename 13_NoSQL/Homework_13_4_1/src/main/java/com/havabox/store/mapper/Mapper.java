package com.havabox.store.mapper;

import com.havabox.store.dto.AbstractBaseDto;
import com.havabox.store.model.AbstractBaseEntity;

public interface Mapper<V extends AbstractBaseEntity, K extends AbstractBaseDto> {

  K toDto(V v);

  V toEntity(K k);

}
