package main.controller;

import main.dto.UserDto;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<UserDto> get() {
        return service.getAll();
    }

    @GetMapping("/users/{id}")
    public Optional<UserDto> get(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("/users")
    public UserDto create(@RequestBody UserDto user) {
        return service.add(user);
    }

    @PostMapping("/users/{id}")
    public ResponseEntity create(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping("/users")
    public List<UserDto> update(@RequestBody List<UserDto> users) {
        return service.update(users);
    }

    @PutMapping("/users/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto user) {
        user.setId(id);
        return service.update(user);
    }

    @DeleteMapping("/users")
    public ResponseEntity clear() {
        service.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/users/{id}")
    public Optional<UserDto> delete(@PathVariable Integer id) {
        return service.delete(id);
    }

}
