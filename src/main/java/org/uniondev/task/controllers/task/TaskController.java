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
    public TaskDtoResponse findById(@PathVariable String id) {
        return taskService.findTaskById(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDtoResponse> findAllTasksByTitle(
            @RequestParam String title,
            @RequestParam int size,
            @RequestParam int offset) {
        return taskService.findAllTasksByTitle(title, size, offset);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDtoResponse createTask(@RequestBody TaskDtoCreate taskDtoCreate) {
        return taskService.createTask(taskDtoCreate);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateTask(@RequestBody TaskDtoUpdate taskDtoUpdate) {
        taskService.updateTask(taskDtoUpdate);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaskById(@PathVariable String id) {
        taskService.deleteTaskById(id);
    }





}
