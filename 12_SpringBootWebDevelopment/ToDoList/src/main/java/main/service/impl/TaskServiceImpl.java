package main.service.impl;

import main.dto.TaskDto;
import main.mapper.TaskMapper;
import main.entity.TaskEntity;
import main.repository.TaskRepository;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    @Override
    @Transactional
    public TaskDto add(TaskDto taskDto) {
        TaskEntity task = mapper.toEntity(taskDto);
        repository.saveAndFlush(task);
        taskDto.setId(task.getId());
        return taskDto;
    }

    @Override
    @Transactional
    public Optional<TaskDto> delete(long id) {
        Optional<TaskEntity> taskOptional = repository.findById(id);
        if (taskOptional.isEmpty()) {
            return Optional.ofNullable(null);
        }
        repository.deleteById(id);
        return Optional.of(mapper.toDto(taskOptional.get()));
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Optional<TaskDto> findById(long id) {
        return Optional.ofNullable(mapper.toDto(repository.findById(id).orElse(null)));
    }

    @Override
    @Transactional
    public TaskDto update(TaskDto taskDto) {
        TaskEntity task = mapper.toEntity(taskDto);
        repository.saveAndFlush(task);
        return taskDto;
    }

    @Override
    @Transactional
    public List<TaskDto> update(List<TaskDto> tasksDto) {
        List<TaskEntity> tasks = new ArrayList<>();
        tasksDto.forEach(taskDto -> {
            tasks.add(mapper.toEntity(taskDto));
        });
        List<TaskDto> result = new ArrayList<>();
        repository.saveAll(tasks).forEach(task -> {
            result.add(mapper.toDto(task));
        });
        return result;
    }

    @Override
    public List<TaskDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

}
