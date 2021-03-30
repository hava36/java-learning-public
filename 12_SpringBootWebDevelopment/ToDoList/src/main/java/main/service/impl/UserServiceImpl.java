package main.service.impl;

import main.config.jwt.JwtProvider;
import main.controller.auth.AuthRequest;
import main.controller.auth.AuthResponse;
import main.dto.RoleDto;
import main.dto.UserDto;
import main.mapper.UserMapper;
import main.entity.RoleEntity;
import main.entity.UserEntity;
import main.repository.RoleRepository;
import main.repository.UserRepository;
import main.service.RoleService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto add(UserDto userDto) {
        RoleDto userRole = roleService.findByName("USER");
        userDto.setRole(userRole);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity user = mapper.toEntity(userDto);
        userRepository.saveAndFlush(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    @Transactional
    public Optional<UserDto> delete(long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return Optional.ofNullable(null);
        }
        userRepository.deleteById(id);
        return Optional.of(mapper.toDto(userOptional.get()));
    }

    @Override
    @Transactional
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public Optional<UserDto> findById(long id) {
        return Optional.ofNullable(mapper.toDto(userRepository.findById(id).orElse(null)));
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        UserEntity user = mapper.toEntity(userDto);
        userRepository.saveAndFlush(user);
        return userDto;
    }

    @Override
    @Transactional
    public List<UserDto> update(List<UserDto> usersDto) {
        List<UserEntity> users = new ArrayList<>();
        usersDto.forEach(userDto -> {
            users.add(mapper.toEntity(userDto));
        });
        List<UserDto> result = new ArrayList<>();
        userRepository.saveAll(users).forEach(user -> {
            result.add(mapper.toDto(user));
        });
        return result;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findByLogin(String login) {
        UserEntity userEntity = userRepository.findByLogin(login);
        return mapper.toDto(userEntity);
    }

    @Override
    public UserDto findByLoginAndPassword(String login, String password) {
        UserDto userDto = findByLogin(login);
        if (userDto != null) {
            if (passwordEncoder.matches(password, userDto.getPassword())) {
                return userDto;
            }
        }
        return null;
    }

    @Override
    public AuthResponse auth(AuthRequest request) {
        UserDto userDto = findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (userDto == null) return null;
        String token = jwtProvider.generateToken(userDto.getLogin());
        return new AuthResponse(token);
    }

}
