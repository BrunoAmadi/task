package org.uniondev.task.models.task.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.uniondev.task.models.task.entity.Task;
import org.uniondev.task.models.task.enums.TaskStatus;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskEntityTest {
    @Test
    @DisplayName("GIVEN valid params WHEN call constructor THEN return TaskEntity")
    public void createTaskEntityWhenCallConstructor() {
        //ARRANGE
        var id = UUID.randomUUID();
        var title = "title";
        var description = "description";
        var status = TaskStatus.TODO;


        //ACTION
        var taskEntity = new TaskEntity(id, title, description, status);

        //ASSERTS
        assertNotNull(taskEntity);
        assertEquals(id, taskEntity.id());
        assertEquals(title, taskEntity.title());
        assertEquals(description, taskEntity.description());
        assertEquals(status.name(), taskEntity.status());
    }

    @Test
    @DisplayName("GIVEN task like param WHEN call method from THEN return TaskEntity")
    public void createTaskEntityWhenGivenValidParams() {
        //ARRANGE
        var title = "title";
        var description = "description";
        var status = TaskStatus.TODO.name();

        //ACTION
        var task = Task.create(title, description);
        var taskEntity = TaskEntity.from(task);
        var id = taskEntity.id();

        //ASSERTS
        assertNotNull(taskEntity);
        assertInstanceOf(TaskEntity.class, taskEntity);
        assertEquals(id, taskEntity.id());
        assertEquals(title, taskEntity.title());
        assertEquals(description, taskEntity.description());
        assertEquals(status, taskEntity.status());

    }

    @Test
    @DisplayName("GIVEN TaskEntity like param WHEN call method toDomain THEN return Task")
    public void toDomainWhenGivenValidParams() {
        //ARRANGE
        var id = UUID.randomUUID();
        var title = "title";
        var description = "description";
        var status = TaskStatus.TODO;

        //ACTION
        var taskEntity = new TaskEntity(id, title, description, status);
        var task = TaskEntity.toDomain(taskEntity);

        //ASSERTS
        assertNotNull(task);
        assertInstanceOf(Task.class, task);
        assertEquals(id, task.id());
        assertEquals(title, task.title());
        assertEquals(description, task.description());
        assertEquals(status, task.status());
    }


    @Test
    @DisplayName("GIVEN  status different of done WHEN call method isDone THEN return false")
    public void whenStatusIsNotDoneCallMethodIsDoneShouldReturnFalse() {

        //ARRANGE
        var id = UUID.randomUUID();
        var title = "title";
        var description = "description";
        var status = TaskStatus.TODO;

        //ACTION
        var task = new Task(id, title, description, status);
        var taskEntity = TaskEntity.from(task);
        var isDone = taskEntity.isDone();

        // ASSERTS
        assertNotNull(taskEntity);
        assertEquals(id, taskEntity.id());
        assertEquals(title, taskEntity.title());
        assertEquals(description, taskEntity.description());
        assertEquals(status.name(), taskEntity.status());
        assertFalse(isDone);


    }

    @Test
    @DisplayName("GIVEN  status different of done WHEN call method isDone THEN return false")
    public void whenStatusIsDoneCallMethodIsDoneShouldReturnTrue() {

        //ARRANGE
        var id = UUID.randomUUID();
        var title = "title";
        var description = "description";
        var status = TaskStatus.DONE;

        //ACTION
        var task = new Task(id, title, description, status);
        var taskEntity = TaskEntity.from(task);
        var createdAt = taskEntity.createdAt();
        var isDone = taskEntity.isDone();

        // ASSERTS
        assertNotNull(taskEntity);
        assertEquals(id, taskEntity.id());
        assertEquals(title, taskEntity.title());
        assertEquals(description, taskEntity.description());
        assertEquals(createdAt, taskEntity.createdAt());
        assertEquals(status.name(), taskEntity.status());

        assertTrue(isDone);


    }
}