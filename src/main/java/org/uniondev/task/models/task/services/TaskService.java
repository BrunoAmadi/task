package org.uniondev.task.models.task.services;
import org.springframework.stereotype.Service;
import org.uniondev.task.controllers.task.dto.TaskDtoCreate;
import org.uniondev.task.controllers.task.dto.TaskDtoResponse;
import org.uniondev.task.controllers.task.dto.TaskDtoUpdate;
import org.uniondev.task.models.task.entity.Task;
import org.uniondev.task.models.task.repository.JPATaskRepositoryInterface;
import org.uniondev.task.models.task.repository.TaskEntity;
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
        var task = Task.create(
                taskDtoCreate.title(),
                taskDtoCreate.description()
        );

        var taskEntity = repository.save(TaskEntity.from(task));
        return TaskDtoResponse.from(taskEntity);

    }

    @Override
    public void updateTask(TaskDtoUpdate taskDtoUpdate) {
       var updated = repository.updateTask(
               taskDtoUpdate.id(),
               taskDtoUpdate.title(),
               taskDtoUpdate.description(),
               taskDtoUpdate.status());

       if (updated == 0) {
           throw new TaskNotFoundException("Task not found in database" + taskDtoUpdate.id());
       }

    }


    @Override
    public TaskDtoResponse findTaskById(String id) {
            var entity = repository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new TaskNotFoundException("Task not found" + id));
            return TaskDtoResponse.from(entity);
    }


    @Override
    public List<TaskDtoResponse> findAllTasksByTitle(String title, int size, int offset) {
       var listTasks = repository.findAllByTitle(title, size, offset);
       return listTasks.stream().map(TaskDtoResponse::from).toList();
    }


    @Override
    public void deleteTaskById(String id) {


        var deleted = repository.deleteTaskById(UUID.fromString(id));
        if (deleted == 0) {
            throw new TaskNotFoundException("Task not found in database" + id);
        }

    }


}
