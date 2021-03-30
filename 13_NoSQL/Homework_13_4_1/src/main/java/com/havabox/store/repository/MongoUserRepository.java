package com.havabox.store.repository;

import com.havabox.store.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<User, String> {

}
