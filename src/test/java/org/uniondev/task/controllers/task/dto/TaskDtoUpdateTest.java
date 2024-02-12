package org.uniondev.task.controllers.task.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.uniondev.task.models.task.enums.TaskStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskDtoUpdateTest {


    @Test
    @DisplayName("GIVEN valid params WHEN call constructor SHOULD return TaskDtoUpdate")
    public void shouldCreateTaskDtoUpdateWhenCallConstructor() {

        //ARRANGE
        var id = UUID.randomUUID().toString();
        var title = "title";
        var description = "description";
        var status = TaskStatus.DONE.name();


        //ACTION
        TaskDtoUpdate taskDtoUpdate = new TaskDtoUpdate(id,title, description,status);

        //ASSERT
        assertNotNull(taskDtoUpdate);
        assertInstanceOf(TaskDtoUpdate.class, taskDtoUpdate);
        assertEquals(title, taskDtoUpdate.title());
        assertEquals(description, taskDtoUpdate.description());

    }
}