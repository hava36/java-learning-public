package main.controller;

import main.dto.TaskDto;
import main.repository.TaskRepository;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String index(Model model) {
        Collection<TaskDto> tasks = taskService.getAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "index";
    }

}
