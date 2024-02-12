package org.uniondev.task.controllers.task.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.uniondev.task.models.task.entity.Task;
import org.uniondev.task.models.task.enums.TaskStatus;
import org.uniondev.task.models.task.repository.TaskEntity;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskDtoResponseTest {



    @Test
    @DisplayName("GIVEN valid params WHEN call constructor SHOULD return TaskDtoResponse")
    public void shouldCreateTaskDtoResponseWhenCallConstructor() {
        //ARRANGE
        String id = "id";
        String title = "title";
        String description = "description";
        String status = "status";
        Instant updatedAt = Instant.now();

        //ACTION
        TaskDtoResponse taskDtoResponse = new TaskDtoResponse(id,title, description,status,updatedAt);

        //ASSERT
        assertNotNull(taskDtoResponse);
        assertInstanceOf(TaskDtoResponse.class, taskDtoResponse);
        assertEquals(id, taskDtoResponse.id());
        assertEquals(title, taskDtoResponse.title());
        assertEquals(description, taskDtoResponse.description());
        assertEquals(status, taskDtoResponse.status());
        assertEquals(updatedAt, taskDtoResponse.updatedAt());

    }


    @Test
    @DisplayName("GIVEN TaskEntity like param WHEN call from method SHOULD return TaskDtoResponse")
    public void shouldCreateTaskDtoResponseWhenGivenValidParams() {
        //ARRANGE
        UUID id = UUID.randomUUID();
        String title = "title";
        String description = "description";
        TaskStatus status = TaskStatus.TODO;

        //ACTION
        TaskEntity taskEntity = new TaskEntity(id, title, description, status);
        TaskDtoResponse taskDtoResponse = TaskDtoResponse.from(taskEntity);

        //ASSERT
        assertNotNull(taskDtoResponse);
        assertInstanceOf(TaskDtoResponse.class, taskDtoResponse);
        assertEquals(id.toString(), taskDtoResponse.id());
        assertEquals(title, taskDtoResponse.title());
        assertEquals(description, taskDtoResponse.description());
        assertEquals(status.name(), taskDtoResponse.status());
    }
}