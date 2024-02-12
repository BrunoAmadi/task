package org.uniondev.task.controllers.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.uniondev.task.models.task.repository.TaskEntity;

import java.time.Instant;

public record TaskDtoResponse(
        @Schema(name = "id", example = "bc20a5c7-11a9-4fa9-b6ca-a924eb90eb6c")
        String id,
        @Schema(name = "title", example = "Task Title")
        String title,
        @Schema(name = "description", example = "Task description")
        String description,
        @Schema(name = "status", example = "DONE")
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
