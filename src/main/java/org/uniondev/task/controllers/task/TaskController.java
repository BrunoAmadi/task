package org.uniondev.task.controllers.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.uniondev.task.controllers.task.dto.TaskDtoCreate;
import org.uniondev.task.controllers.task.dto.TaskDtoResponse;
import org.uniondev.task.controllers.task.dto.TaskDtoUpdate;
import org.uniondev.task.models.task.services.TaskServiceInterface;

import java.util.List;

@RestController
@RequestMapping( "/tasks")
public class TaskController {

    private final TaskServiceInterface taskService;

    public TaskController(TaskServiceInterface taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDtoResponse find(@PathVariable String id) {
        return taskService.findTaskById(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDtoResponse> findAll(
            @RequestParam String title,
            @RequestParam int size,
            @RequestParam int offset) {
        return taskService.findAllTasksByTitle(title, size, offset);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDtoResponse create(@RequestBody TaskDtoCreate taskDtoCreate) {
        return taskService.createTask(taskDtoCreate);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TaskDtoResponse update(@RequestBody TaskDtoUpdate taskDtoUpdate) {
      return taskService.updateTask(taskDtoUpdate);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        taskService.deleteTaskById(id);
    }





}
