package org.uniondev.task.models.task.services;

import org.springframework.stereotype.Service;
import org.uniondev.task.controllers.task.dto.TaskDtoCreate;
import org.uniondev.task.controllers.task.dto.TaskDtoResponse;
import org.uniondev.task.controllers.task.dto.TaskDtoUpdate;
import org.uniondev.task.models.task.entity.Task;
import org.uniondev.task.models.task.entity.exceptions.TaskIllegalArgumentException;
import org.uniondev.task.models.task.enums.TaskStatus;
import org.uniondev.task.models.task.repository.JPATaskRepositoryInterface;
import org.uniondev.task.models.task.repository.TaskEntity;
import org.uniondev.task.models.task.services.exceptions.TaskInvalidArgumentException;
import org.uniondev.task.models.task.services.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService implements TaskServiceInterface {
    private final JPATaskRepositoryInterface repository;

    public TaskService(JPATaskRepositoryInterface jpaTaskRepository) {
        this.repository = jpaTaskRepository;
    }

    @Override
    public TaskDtoResponse createTask(TaskDtoCreate taskDtoCreate) {
        try {
            var task = Task.create(
                    taskDtoCreate.title(),
                    taskDtoCreate.description()
            );

            var taskEntity = repository.save(TaskEntity.from(task));
            return TaskDtoResponse.from(taskEntity);

        } catch (TaskIllegalArgumentException e) {
            throw new TaskInvalidArgumentException(e.getMessage());
        }

    }

    @Override
    public TaskDtoResponse updateTask(TaskDtoUpdate taskDtoUpdate) {
        var id = UUID.fromString(taskDtoUpdate.id());

        var entity = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found " + id));

        try {
            var task = TaskEntity.toDomain(entity);
            task.updateAll(
                    taskDtoUpdate.title(),
                    taskDtoUpdate.description(),
                    taskDtoUpdate.status()
            );

            var taskEntity = TaskEntity.from(task);

           repository.updateTask(
                    taskEntity.id(),
                    taskEntity.title(),
                    taskEntity.description(),
                    taskEntity.status()
            );

            return TaskDtoResponse.from(taskEntity);

        } catch (TaskIllegalArgumentException e) {
            throw new TaskInvalidArgumentException(e.getMessage());
        }

    }


    @Override
    public TaskDtoResponse findTaskById(String id) {
        var entity = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TaskNotFoundException("Task not found " + id));
        return TaskDtoResponse.from(entity);
    }


    @Override
    public List<TaskDtoResponse> findAllTasksByTitle(String title, int size, int offset) {
        var listTasks = repository.findAllByTitle(title, size, offset);
        return listTasks.stream().map(TaskDtoResponse::from).toList();
    }


    @Override
    public void deleteTaskById(String id) {
        var entity = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TaskNotFoundException("There is no task with this ID so it can be deleted " + id));

        if (entity.isDone()) {
            throw new TaskInvalidArgumentException("It is not possible to delete a task that has already been completed " + id);
        }

        repository.deleteTaskById(UUID.fromString(id));
    }


}
