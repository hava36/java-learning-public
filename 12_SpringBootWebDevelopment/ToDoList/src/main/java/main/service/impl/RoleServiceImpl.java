package main.service.impl;

import main.dto.RoleDto;
import main.entity.RoleEntity;
import main.mapper.RoleMapper;
import main.repository.RoleRepository;
import main.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper mapper;

    @Override
    @Transactional
    public RoleDto add(RoleDto roleDto) {
        RoleEntity roleEntity = mapper.toEntity(roleDto);
        roleRepository.save(roleEntity);
        roleDto.setId(roleEntity.getId());
        return roleDto;
    }

    @Override
    @Transactional
    public Optional<RoleDto> delete(long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteAll() {

    }

    @Override
    public Optional<RoleDto> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<RoleDto> getAll() {
        return null;
    }

    @Override
    @Transactional
    public RoleDto update(RoleDto v) {
        return null;
    }

    @Override
    @Transactional
    public List<RoleDto> update(List<RoleDto> v) {
        return null;
    }

    @Override
    @Transactional
    public RoleDto findByName(String name) {
        RoleEntity roleEntity = roleRepository.findByName(name);
        return mapper.toDto(roleEntity);
    }


}
