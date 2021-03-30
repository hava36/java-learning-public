package main.mapper;

import main.dto.AbstractBaseDto;
import main.entity.AbstractBaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper<V extends AbstractBaseEntity, T extends AbstractBaseDto> implements Mapper<V, T> {

    @Autowired
    ModelMapper mapper;

    private Class<V> entityClass;
    private Class<T> dtoClass;

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
