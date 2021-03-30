package main.service;

import main.dto.RoleDto;
import main.entity.RoleEntity;

public interface RoleService extends Service<RoleDto> {

    RoleDto findByName(String name);

}
