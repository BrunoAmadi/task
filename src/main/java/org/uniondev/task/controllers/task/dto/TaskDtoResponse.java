package org.uniondev.task.controllers.task.dto;

import org.uniondev.task.models.task.repository.TaskEntity;

import java.time.Instant;

public record TaskDtoResponse(
        String id,
        String title,
        String description,
        String status,
        Instant updatedAt
) {
    public static TaskDtoResponse from(TaskEntity task) {
        return new TaskDtoResponse(
                task.id().toString(),
                task.title(),
                task.description(),
                task.status().toString(),
                task.updatedAt()
        );
    }

}
