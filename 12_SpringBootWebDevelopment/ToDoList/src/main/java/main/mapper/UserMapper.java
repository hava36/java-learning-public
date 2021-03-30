package main.mapper;

import main.dto.UserDto;
import main.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper extends AbstractMapper<UserEntity, UserDto> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    public UserMapper() {
        super(UserEntity.class, UserDto.class);
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        if (Objects.isNull(dto)) return null;
        UserEntity entity =  super.toEntity(dto);
        entity.setRole(roleMapper.toEntity(dto.getRole()));
        return entity;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        if (Objects.isNull(entity)) return null;
        UserDto userDto =  super.toDto(entity);
        userDto.setRole(roleMapper.toDto(entity.getRole()));
        return userDto;
    }

}
