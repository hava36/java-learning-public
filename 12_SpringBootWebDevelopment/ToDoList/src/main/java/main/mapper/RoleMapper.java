package main.mapper;

import main.dto.RoleDto;
import main.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends AbstractMapper<RoleEntity, RoleDto> {

    @Autowired
    public RoleMapper() {
        super(RoleEntity.class, RoleDto.class);
    }

}
