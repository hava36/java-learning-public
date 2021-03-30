package com.havabox.store.mapper;

import com.havabox.store.dto.AbstractBaseDto;
import com.havabox.store.model.AbstractBaseEntity;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<V extends AbstractBaseEntity, T extends AbstractBaseDto> implements
    Mapper<V, T> {

  @Autowired
  ModelMapper mapper;

  private final Class<V> entityClass;
  private final Class<T> dtoClass;

  public AbstractMapper(Class<V> entityClass, Class<T> dtoClass) {
    this.entityClass = entityClass;
    this.dtoClass = dtoClass;
  }

  @Override
  public V toEntity(T dto) {
    return Objects.isNull(dto) ? null : mapper.map(dto, entityClass);
  }

  @Override
  public T toDto(V entity) {
    return Objects.isNull(entity) ? null : mapper.map(entity, dtoClass);
  }

}
