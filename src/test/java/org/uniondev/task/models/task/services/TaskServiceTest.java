package org.uniondev.task.models.task.services;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uniondev.task.controllers.task.dto.TaskDtoCreate;
import org.uniondev.task.controllers.task.dto.TaskDtoResponse;
import org.uniondev.task.controllers.task.dto.TaskDtoUpdate;
import org.uniondev.task.models.task.entity.Task;
import org.uniondev.task.models.task.enums.TaskStatus;
import org.uniondev.task.models.task.repository.JPATaskRepositoryInterface;
import org.uniondev.task.models.task.repository.TaskEntity;
import org.uniondev.task.models.task.services.exceptions.TaskInvalidArgumentException;
import org.uniondev.task.models.task.services.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private JPATaskRepositoryInterface repository;
    @InjectMocks
    private TaskService taskService;


    @Test
    @DisplayName("GIVEN valid params WHEN call createTask SHOULD return TaskDtoResponse")
    public void testCreateTask() {

        //ARRANGE
        var title = "title";
        var description = "description";
        var taskDtoCreate = new TaskDtoCreate(title, description);
        when(repository.save(any()))
                .thenReturn(TaskEntity.from(Task.create(title, description)));


        //ACTION
        var taskDtoResponse = taskService.createTask(taskDtoCreate);
        var id = taskDtoResponse.id();

        //ASSERTS
        assertNotNull(taskDtoResponse);
        assertInstanceOf(TaskDtoResponse.class, taskDtoResponse);

    }


    @Test
    @DisplayName("GIVEN invalid title WHEN call createTask SHOULD error")
    public void shouldErrorCreateTaskWhenTitleIsInvalid() {

        //ARRANGE
        var title = "ti";
        var description = "description";
        var taskDtoCreate = new TaskDtoCreate(title, description);
        var message = "Title must be between 3 and 50 characters";

        //ACTION
        var error = assertThrows(TaskInvalidArgumentException.class, () ->
            taskService.createTask(taskDtoCreate)
        );

        //ASSERTS
        assertEquals(error.getMessage(), message);
        assertInstanceOf(TaskInvalidArgumentException.class, error);

    }


    @Test
    @DisplayName("GIVEN valid params WHEN call update SHOULD return TaskDtoResponse updated")
    public void shouldUpdateTaskWhenGivenValidParams() {

        //ARRANGE
        var title = "title";
        var description = "description";
        var task = Task.create(title, description);
        var id = task.id();

        var titleUpdated = "titleUpdated";
        var descriptionUpdated = "descriptionUpdated";
        var status = TaskStatus.DONE.name();
        var taskDtoUpdate = new TaskDtoUpdate(id.toString(), titleUpdated, descriptionUpdated, status);


        when(repository.findById(id))
                .thenReturn(Optional.of(TaskEntity.from(task)));


        doNothing().when(repository).updateTask(id, titleUpdated, descriptionUpdated, status);

        //ACTION
       var taskDtoResponse = taskService.updateTask(taskDtoUpdate);


        //ASSERTS
        assertNotNull(taskDtoResponse);
        assertInstanceOf(TaskDtoResponse.class, taskDtoResponse);
        assertEquals(id.toString(), taskDtoResponse.id());
        assertEquals(titleUpdated, taskDtoResponse.title());
        assertEquals(descriptionUpdated, taskDtoResponse.description());
        assertEquals(status, taskDtoResponse.status());
        assertNotEquals(description, taskDtoResponse.description());
        assertNotEquals(title, taskDtoResponse.title());


    }

    @Test
    @DisplayName("GIVEN invalid params WHEN call update SHOULD throw errors")
    public void shouldThrowErrorsWhenGivenInvalidID() {

        //ARRANGE
        var id = UUID.randomUUID();
        var title = "title";
        var description = "description";
        var status = "DONE";
        var taskDtoUpdate = new TaskDtoUpdate(id.toString(), title, description, status);

        var message = "Task not found " +id;
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        //ACTION
        var error = assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskDtoUpdate));

        //Assert
        verify(repository, times(1)).findById(id);
        assertEquals(message, error.getMessage());
        assertInstanceOf(TaskNotFoundException.class, error);

    }

    @Test
    @DisplayName("GIVEN invalid params WHEN call update SHOULD throw errors")
    public void shouldThrowErrorsWhenGivenTitleLessThanThreeOrMoreThanFifty() {

        //ARRANGE
        var title = "title";
        var description = "description";
        var task = Task.create(title, description);
        var id = task.id();

        var titleUpdated = "ti";
        var descriptionUpdated = "descriptionUpdated";
        var status = TaskStatus.DONE.name();
        var taskDtoUpdate = new TaskDtoUpdate(id.toString(), titleUpdated, descriptionUpdated, status);

        var message = "Title must be between 3 and 50 characters";
        when(repository.findById(id))
                .thenReturn(Optional.of(TaskEntity.from(task)));

        //ACTION
        var error = assertThrows(TaskInvalidArgumentException.class, () -> taskService.updateTask(taskDtoUpdate));

        //Assert
        verify(repository, times(1)).findById(id);
        assertEquals(message, error.getMessage());
        assertInstanceOf(TaskInvalidArgumentException.class, error);

    }



    @Test
    @DisplayName("GIVEN valid id WHEN call findTaskById SHOULD return TaskDtoResponse")
    public void shouldFindTaskById() {

        //ARRANGE
        var title = "title";
        var description = "description";
        var task = Task.create(title, description);
        var id = task.id();

        when(repository.findById(id))
                .thenReturn(Optional.of(TaskEntity.from(task)));

        //ACTION
        var taskDtoResponse = taskService.findTaskById(id.toString());

        //ASSERTS
        verify(repository, times(1)).findById(id);
        assertNotNull(taskDtoResponse);
        assertInstanceOf(TaskDtoResponse.class, taskDtoResponse);
        assertEquals(task.id().toString(), taskDtoResponse.id());
        assertEquals(task.title(), taskDtoResponse.title());
        assertEquals(task.description(), taskDtoResponse.description());
        assertEquals(task.status().name(), taskDtoResponse.status());

    }


    @Test
    @DisplayName("Given invalid Id WHEN call findTaskById SHOULD throw error")
    public void shouldThrowErrorWhenFindTaskById() {
        //ARRANGE
        var id = UUID.randomUUID();
        var message = "Task not found " + id;
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        //ACTION
        var error = assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById(id.toString()));


        //ASSERTS
        verify(repository, times(1)).findById(id);
        assertEquals(message, error.getMessage());
        assertInstanceOf(TaskNotFoundException.class, error);
    }


    @Test
    @DisplayName("GIVEN valid params WHEN call FindAllTasks SHOULD return list TaskDtoResponse")
    public void shouldFindAllTasks() {
        //ARRANGE
        var title = "title";
        var size = 3;
        var offset = 1;

        List<TaskEntity> taskEntities = List.of(
                TaskEntity.from(Task.create("title", "description")),
                TaskEntity.from(Task.create("titl", "description")),
                TaskEntity.from(Task.create("tit", "description"))
        );

        when(repository.findAllByTitle(title, size, offset))
                .thenReturn(taskEntities);


        //ACTION
        var listTaskDtoResponse = taskService.findAllTasksByTitle(title, size, offset);

        verify(repository, times(1)).findAllByTitle(title, size, offset);
        //ASSERTS
        assertNotNull(listTaskDtoResponse);
        assertInstanceOf(List.class, listTaskDtoResponse);
        assertEquals(size, listTaskDtoResponse.size());

 }


    @Test
    @DisplayName("SHOULD throw error WHEN call delete and task status is done")
    public void shouldThrowErrorWhenDeleteTaskStatusIsDone() {
        //ARRANGE
        var title = "title";
        var description = "description";
        var task = Task.create(title, description);
        var id = task.id();

        var message = "It is not possible to delete a task that has already been completed " + id;
        task.updateStatus(TaskStatus.DONE.name());
        when(repository.findById(id))
                .thenReturn(Optional.of(TaskEntity.from(task)));

        //ACTION
        var error = assertThrows(TaskInvalidArgumentException.class, () -> taskService.deleteTaskById(id.toString()));

        //ASSERTS
        verify(repository, never()).deleteTaskById(id);
        assertEquals(message, error.getMessage());
        assertInstanceOf(TaskInvalidArgumentException.class, error);



    }

    @Test
    @DisplayName("GIVEN invalid id WHEN call delete SHOULD throw error")
    public void shouldThrowErrorWhenDelete() {
        //ARRANGE
        var id = UUID.randomUUID();
        var message = "There is no task with this ID so it can be deleted " + id;
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        //ACTION
        var error = assertThrows(TaskNotFoundException.class, () -> taskService.deleteTaskById(id.toString()));
        verify(repository, never()).deleteTaskById(id);

        //ASSERTS
        assertEquals(message, error.getMessage());
        assertInstanceOf(TaskNotFoundException.class, error);

    }

    @Test
    @DisplayName("SHOULD delete task WHEN id is valid and status is not done")
    public void shouldDeleteTask() {
        //ARRANGE
        var title = "title";
        var description = "description";
        var task = Task.create(title, description);
        var id = task.id();
        when(repository.findById(id))
                .thenReturn(Optional.of(TaskEntity.from(task)));

        //ACTION
        taskService.deleteTaskById(id.toString());

        //ASSERTS
        verify(repository, times(1)).deleteTaskById(id);


    }

}