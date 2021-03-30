package com.havabox.store.controller;

import com.havabox.store.dto.UserDto;
import com.havabox.store.service.Service;
import com.havabox.store.service.impl.MongoUserServiceImpl;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private MongoUserServiceImpl userService;

  @PostMapping("/users")
  public ResponseEntity<String> add(@RequestBody UserDto userDto) {
    userService.add(userDto);
    return ResponseEntity.status(HttpStatus.OK).body(userDto.getId());
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserDto> find(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.find(id));
  }

  @GetMapping("/users")
  public Collection<UserDto> get() {
    return userService.findAll();
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> delete(@PathVariable String id) {
    userService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body(id);
  }

}
