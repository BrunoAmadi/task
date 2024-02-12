package org.uniondev.task.controllers.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public record TaskDtoCreate(
        @Schema(name = "title", example = "Task Title")
        String title,
        @Schema(name = "description", example = "Task description")
        String description) {

}
