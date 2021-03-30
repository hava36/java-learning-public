package com.havabox.store.service.impl;

import com.havabox.store.dto.UserDto;
import com.havabox.store.mapper.Mapper;
import com.havabox.store.model.User;
import com.havabox.store.repository.RedisUserRepository;
import com.havabox.store.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisUserServiceImpl implements UserService {

  @Autowired
  private RedisUserRepository userRepository;

  @Autowired
  private Mapper<User, UserDto> mapper;

  @Override
  @Transactional
  public void add(UserDto userDto) {
    userRepository.add(mapper.toEntity(userDto));
  }

  @Override
  @Transactional
  public void delete(String id) {
    userRepository.delete(id);
  }

  @Override
  public UserDto find(String id) {
    return mapper.toDto(userRepository.find(id));
  }

  @Override
  public Collection<UserDto> findAll() {
    Collection<UserDto> result = new ArrayList<>();
    userRepository.findAll().forEach(user -> result.add(mapper.toDto(user)));
    return result;
  }
}
