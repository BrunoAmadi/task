package org.uniondev.task.models.task.services;

import org.springframework.stereotype.Service;
import org.uniondev.task.controllers.task.dto.TaskDtoCreate;
import org.uniondev.task.controllers.task.dto.TaskDtoResponse;
import org.uniondev.task.controllers.task.dto.TaskDtoUpdate;

import java.util.List;
public interface TaskServiceInterface {

    TaskDtoResponse createTask(TaskDtoCreate taskDtoCreate);
    void updateTask(TaskDtoUpdate taskDtoUpdate);

    TaskDtoResponse findTaskById(String id);

    List<TaskDtoResponse> findAllTasksByTitle(String title, int size, int offset);


    void deleteTaskById(String id);
}
