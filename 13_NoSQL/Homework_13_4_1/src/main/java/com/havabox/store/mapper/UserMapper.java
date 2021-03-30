package com.havabox.store.mapper;

import com.havabox.store.dto.UserDto;
import com.havabox.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

  @Autowired
  public UserMapper() {
    super(User.class, UserDto.class);
  }

}
