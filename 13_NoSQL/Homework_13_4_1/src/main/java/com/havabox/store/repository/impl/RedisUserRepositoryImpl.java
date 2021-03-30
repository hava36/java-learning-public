package com.havabox.store.repository.impl;

import com.havabox.store.model.User;
import com.havabox.store.repository.RedisUserRepository;
import java.util.Collection;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisUserRepositoryImpl implements RedisUserRepository {

  private static final String KEY = "User";
  private final RedisTemplate<String, Object> redisTemplate;
  private HashOperations hashOperations;

  @Autowired
  public RedisUserRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public void add(User user) {
    hashOperations.put(KEY, user.getId(), user);
  }

  @Override
  public void delete(String id) {
    hashOperations.delete(KEY, id);
  }

  @Override
  public User find(String id) {
    return (User) hashOperations.get(KEY, id);
  }

  @Override
  public Collection<User> findAll() {
    return hashOperations.entries(KEY).values();
  }

}
