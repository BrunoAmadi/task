package org.uniondev.task.controllers.task;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.uniondev.task.controllers.task.dto.TaskDtoCreate;
import org.uniondev.task.controllers.task.dto.TaskDtoResponse;
import org.uniondev.task.controllers.task.dto.TaskDtoUpdate;
import org.uniondev.task.models.task.services.TaskServiceInterface;

import java.util.List;
@RestController
@RequestMapping( "api/v1/tasks")

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


    @Operation(summary = "FindAll task", description = "Find all task filtered title, size and offset")
    @ApiResponse(responseCode = "200")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDtoResponse> findAll(
            @RequestParam String title,
            @RequestParam int size,
            @RequestParam int offset) {
        return taskService.findAllTasksByTitle(title, size, offset);
    }


    @Operation(summary = "Create task", description = "Create a task by passing title and description")
    @ApiResponse(responseCode = "201")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDtoResponse create(@RequestBody TaskDtoCreate taskDtoCreate) {
        return taskService.createTask(taskDtoCreate);
    }


    @Operation(summary = "Update task by id", description = "Update a task by passing ID, title, description and status")
    @ApiResponse(responseCode = "200")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TaskDtoResponse update(@RequestBody TaskDtoUpdate taskDtoUpdate) {
      return taskService.updateTask(taskDtoUpdate);
    }


    @Operation(summary = "Delete task by id", description = "Deletes a task that is not in Done status, just Doing or Todo")
    @ApiResponse(responseCode = "204")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        taskService.deleteTaskById(id);
    }





}
