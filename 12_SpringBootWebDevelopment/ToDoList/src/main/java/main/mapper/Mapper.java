package main.mapper;

import main.dto.AbstractBaseDto;
import main.entity.AbstractBaseEntity;

public interface Mapper<V extends AbstractBaseEntity,T extends AbstractBaseDto> {

    V toEntity(T dto);
    T toDto(V entity);

}
