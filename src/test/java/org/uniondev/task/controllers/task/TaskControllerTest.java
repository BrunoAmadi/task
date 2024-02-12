package org.uniondev.task.controllers.task;

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
import org.uniondev.task.models.task.repository.TaskEntity;
import org.uniondev.task.models.task.services.TaskService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;


    @Test
    @DisplayName("GIVEN valid param WHEN call createTask SHOULD return TaskDtoResponse")
    public void shouldReturnTaskDtoResponseGivenValidParam() {

        //ARRANGE
        var title = "title";
        var description = "description";
        var task = Task.create(title, description);
        var taskEntity = TaskEntity.from(task);
        var id = taskEntity.id().toString();
        when(taskService.findTaskById(anyString())).thenReturn(TaskDtoResponse.from(taskEntity));


        //action
        var taskDtoResponse = taskController.find(id);

        //assert
        verify(taskService, times(1)).findTaskById(id);
        assertNotNull(taskDtoResponse);
        assertInstanceOf(TaskDtoResponse.class, taskDtoResponse);
        assertEquals(title, taskDtoResponse.title());
        assertEquals(description, taskDtoResponse.description());
        assertEquals(taskEntity.id().toString(), taskDtoResponse.id());
        assertEquals(taskEntity.status(), taskDtoResponse.status());
    }

    @Test
    @DisplayName("GIVEN valid params WHEN call findAllTaskById SHOULD return list of TaskDtoResponse")
    public void shouldReturnListOfTaskDtoResponseGivenValidParam() {
        //ARRANGE
        var title = "title";
        var size = 2;
        var offset = 0;


        var list = List.of(
                TaskDtoResponse.from(TaskEntity.from(Task.create("title1", "description1"))),
                TaskDtoResponse.from(TaskEntity.from(Task.create("title2", "description2")))
        );

        when(taskService.findAllTasksByTitle(title, size, offset)).thenReturn(list);

        //ACTION
        var taskDtoResponseList = taskController.findAll(title, size, offset);

        //ASSERT
        verify(taskService, times(1)).findAllTasksByTitle(title, size, offset);
        assertNotNull(taskDtoResponseList);
        assertInstanceOf(List.class, taskDtoResponseList);
        assertEquals(2, taskDtoResponseList.size());

    }


    @Test
    @DisplayName("GIVEN valid params WHEN call createTask SHOULD return task created")
    public void shouldReturnTaskDtoCreated() {

        //ARRANGE
        var title = "title";
        var description = "description";
        var task = Task.create(title, description);
        var taskEntity = TaskEntity.from(task);

        var taskDtoCreate = new TaskDtoCreate("title", "description");
        when(taskService.createTask(taskDtoCreate)).thenReturn(TaskDtoResponse.from(taskEntity));

        //ACTION
        var taskDtoResponse = taskController.create(taskDtoCreate);

        //ASSERTS
        verify(taskService, times(1)).createTask(taskDtoCreate);
        assertNotNull(taskDtoResponse);
        assertInstanceOf(TaskDtoResponse.class, taskDtoResponse);
        assertEquals(title, taskDtoResponse.title());
        assertEquals(description, taskDtoResponse.description());
        assertEquals(taskEntity.id().toString(), taskDtoResponse.id());
        assertEquals(taskEntity.status(), taskDtoResponse.status());

    }

    @Test
    @DisplayName("GIVEN valid params WHEN call updateTask SHOULD return task updated")
    public void shouldReturnTaskDtoUpdated() {
        //ARRANGE
        var title = "titleUpdated";
        var description = "descriptionUpdated";
        var task = Task.create(title, description);
        var taskEntity = TaskEntity.from(task);
        var status = TaskStatus.DONE.name();

        var taskDtoUpdate = new TaskDtoUpdate(
                task.id().toString(),
                title,
                description,
                status
        );
        when(taskService.updateTask(taskDtoUpdate)).thenReturn(TaskDtoResponse.from(taskEntity));

        //ACTION
        var taskDtoResponse = taskController.update(taskDtoUpdate);

        //ASSERTS
        verify(taskService, times(1)).updateTask(taskDtoUpdate);
        assertNotNull(taskDtoResponse);
        assertInstanceOf(TaskDtoResponse.class, taskDtoResponse);
        assertEquals(title, taskDtoResponse.title());
        assertEquals(description, taskDtoResponse.description());
        assertEquals(taskEntity.id().toString(), taskDtoResponse.id());
        assertEquals(taskEntity.status(), taskDtoResponse.status());
    }


    @Test
    @DisplayName("GIVEN valid params WHEN call deleteTask SHOULD delete task")
    public void shouldReturnTaskDtoDeleted() {

        //ARRANGE
        var title = "title";
        var description = "description";
        var task = Task.create(title, description);
        var taskEntity = TaskEntity.from(task);
        var id = taskEntity.id().toString();

        doNothing().when(taskService).deleteTaskById(id);

        //ACTION
        taskController.delete(id);

        //ASSERTS
        verify(taskService, times(1)).deleteTaskById(id);

    }


}