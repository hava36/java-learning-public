package com.havabox.store.repository;

import com.havabox.store.model.User;
import java.util.Collection;

public interface RedisUserRepository {

  void add(User user);

  void delete(String id);

  User find(String id);

  Collection<User> findAll();

}
