package com.havabox.store.service.impl;

import com.havabox.store.dto.UserDto;
import com.havabox.store.mapper.Mapper;
import com.havabox.store.model.User;
import com.havabox.store.repository.RedisUserRepository;
import com.havabox.store.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MongoUserServiceImpl implements UserService {

  @Autowired
  private MongoRepository<User, String> userRepository;

  @Autowired
  private Mapper<User, UserDto> mapper;

  @Override
  @Transactional
  public void add(UserDto userDto) {
    userRepository.insert(mapper.toEntity(userDto));
  }

  @Override
  @Transactional
  public void delete(String id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserDto find(String id) {
    Optional<User> optional = userRepository.findById(id);
    return mapper.toDto(optional.orElse(new User()));
  }

  @Override
  public Collection<UserDto> findAll() {
    Collection<UserDto> result = new ArrayList<>();
    userRepository.findAll().forEach(user -> result.add(mapper.toDto(user)));
    return result;
  }
}
