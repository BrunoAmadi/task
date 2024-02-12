package org.uniondev.task.models.task.entity;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.uniondev.task.models.task.entity.exceptions.TaskIllegalArgumentException;
import org.uniondev.task.models.task.enums.TaskStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {

    @Test
    @DisplayName("GIVEN valid params WHEN call create SHOULD return Task object")
    public void taskCreate() {

        //ARRANGE
        String title = "title";
        String description = "description";

        // ACTION
        Task task = Task.create(title, description);

        // ASSERTS
        assertNotNull(task);

        assertEquals(title, task.title());
        assertEquals(description, task.description());
        assertEquals(TaskStatus.TODO, task.status());
    }

    @Test
    @DisplayName("GIVEN valid params WHEN call full constructor Task SHOULD return Task")
    public void CreateTaskUsingFullConstructor() {
        //ARRANGE
        var id = UUID.randomUUID();
        var title = "title";
        var description = "description";
        var status = TaskStatus.TODO;

        // ACTION
        Task task = new Task(id, title, description, status);

        // ASSERTS
        assertNotNull(task);
        assertEquals(id, task.id());
        assertEquals(title, task.title());
        assertEquals(description, task.description());
        assertEquals(status, task.status());
    }

    @Test
    @DisplayName("GIVEN valid params WHEN call updateAll SHOULD update Task")
    public void UpdateTaskWhenCallUpdateAll() {
        //ARRANGE
        var title = "title";
        var description = "description";
        var status = "DONE";

        //ACTION
        Task task = Task.create(title, description);
        task.updateAll(title, description, status);

        //ASSERT
        assertNotNull(task);
        assertEquals(title, task.title());
        assertEquals(description, task.description());
        assertEquals(TaskStatus.DONE, task.status());

    }


    @Test
    @DisplayName("GIVEN title less than 3 characters WHEN call create SHOULD throw errors")
    public void createTaskWithTitleLessThanThreeCharacters() {
        //ARRANGE
        var message = "Title must be between 3 and 50 characters";
        var title = "Ti";
        var description = "description";

        //ACTION

        var error =  assertThrows(TaskIllegalArgumentException.class, () -> Task.create(title, description));

        //ASSERT
        assertEquals(message, error.getMessage());
    }


    @Test
    @DisplayName("GIVEN title more than 50 characters WHEN call create SHOULD throw errors")
    public void createTaskWithTitleMoreThanFiftyCharacters() {
        //ARRANGE
        var message = "Title must be between 3 and 50 characters";
        var title = "";
        var description = "description";

        //ACTION
        var error =  assertThrows(TaskIllegalArgumentException.class, () -> Task.create(title.repeat(51), description));

        //ASSERT
        assertEquals(message, error.getMessage());
    }

    @Test
    @DisplayName("GIVEN description less than 3 characters WHEN call create SHOULD throw errors")
    public void createTaskWithDescriptionLessThanThreeCharacters() {
        //ARRANGE
        var message = "Description must be between 3 and 250 characters";
        var title = "title";
        var description = "De";

        // ACTION
        var error = assertThrows(TaskIllegalArgumentException.class, () ->
                Task.create(title, description)
        );

        // ASSERT
        assertEquals(message, error.getMessage());
    }


    @Test
    @DisplayName("GIVEN description less than 3 characters WHEN call create SHOULD throw errors")
    public void createTaskWithDescriptionMoreThanTwoHundredFiftyCharacters() {
        //ARRANGE
        var message = "Description must be between 3 and 250 characters";
        var title = "title";
        var description = "";

        // ACTION
        var error = assertThrows(TaskIllegalArgumentException.class, () ->
                Task.create(title, description.repeat(251))
        );

        // ASSERT
        assertEquals(message, error.getMessage());
    }


    @Test
    @DisplayName("GIVEN invalid status name WHEN call updateAll SHOULD throw errors")
    public void shouldThrowErrorsWhenGivenInvalidID() {
        //ARRANGE
        var title = "title";
        var description = "description";
        var status = "test";
        var message = "Please insert 'TODO', 'DONE' or 'DOING'";

        var task = Task.create(title, description);

        //ACTION
        var error = assertThrows(TaskIllegalArgumentException.class, () ->
                task.updateAll(title, description, status)
        );

        //Assert
        assertEquals(message, error.getMessage());
        assertInstanceOf(TaskIllegalArgumentException.class, error);

    }
}