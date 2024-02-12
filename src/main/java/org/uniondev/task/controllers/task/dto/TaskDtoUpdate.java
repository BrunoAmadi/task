package org.uniondev.task.controllers.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record TaskDtoUpdate(
        @Schema(name = "id", example = "bc20a5c7-11a9-4fa9-b6ca-a924eb90eb6c")
        String id,
        @Schema(name = "title", example = "new title")
        String title,
        @Schema(name = "description", example = "new description")
        String description,
        @Schema(name = "status", example = "DONE")
        String status) {

}
