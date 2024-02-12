package org.uniondev.task.controllers.task.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskDtoCreateTest {

    @Test
    @DisplayName("GIVEN valid params WHEN call constructor SHOULD return TaskDtoCreate object")
    public void ShouldCreateTaskDtoWhenCallConstructor() {

        //ARRANGE
        String title = "title";
        String description = "description";

        //ACTION
        TaskDtoCreate taskDtoCreate = new TaskDtoCreate(title, description);

        //ASSERT
        assertNotNull(taskDtoCreate);
        assertInstanceOf(TaskDtoCreate.class, taskDtoCreate);
        assertEquals(title, taskDtoCreate.title());
        assertEquals(description, taskDtoCreate.description());

    }

}