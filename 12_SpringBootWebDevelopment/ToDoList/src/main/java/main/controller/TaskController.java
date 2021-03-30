package main.controller;

import main.dto.TaskDto;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/tasks")
    public List<TaskDto> get() {
        return service.getAll();
    }

    @GetMapping("/tasks/{id}")
    public Optional<TaskDto> get(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("/tasks")
    public TaskDto create(@RequestBody TaskDto task) {
        return service.add(task);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity create(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping("/tasks")
    public List<TaskDto> update(@RequestBody List<TaskDto> tasks) {
        return service.update(tasks);
    }

    @PutMapping("/tasks/{id}")
    public TaskDto update(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return service.update(taskDto);
    }

    @DeleteMapping("/tasks")
    public ResponseEntity clear() {
        service.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/tasks/{id}")
    public Optional<TaskDto> delete(@PathVariable Integer id) {
        return service.delete(id);
    }

}
