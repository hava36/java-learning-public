package main.mapper;

import main.dto.TaskDto;
import main.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper extends AbstractMapper<TaskEntity, TaskDto> {

    @Autowired
    public TaskMapper() {
        super(TaskEntity.class, TaskDto.class);
    }

}
