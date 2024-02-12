package org.uniondev.task.models.task.repository;


import jakarta.persistence.*;
import org.uniondev.task.models.task.entity.Task;
import org.uniondev.task.models.task.enums.TaskStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "TB_TASK")
public class TaskEntity {

    @Id
    @Column(name = "id_task")
    private UUID id;

    @Column(name = "nm_title")
    private String title;

    @Column(name = "ds_description")
    private String description;

    @Column(name = "st_status")
    private String status;

    @Column(name = "dt_created")
    private Instant createdAt;

    @Column(name = "dt_updated")
    private Instant updatedAt;


    public TaskEntity() {

    }

    public TaskEntity(UUID id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status.name();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }


    public UUID id() {
        return this.id;
    }

    public String title() {
        return this.title;
    }

    public String description() {
        return description;
    }

    public String status() {
        return this.status;
    }

    public Instant createdAt() {
        return this.createdAt;
    }

    public Instant updatedAt() {
        return this.updatedAt;
    }


    public boolean isDone() {
        if (this.status.equals(TaskStatus.DONE.name())) {
            return true;
        }
        return false;
    }

    public static TaskEntity from(Task task) {
        return new TaskEntity(
                task.id(),
                task.title(),
                task.description(),
                task.status()
        );
    }

    public static Task toDomain(TaskEntity taskEntity) {
        return new Task(
                taskEntity.id(),
                taskEntity.title(),
                taskEntity.description(),
                TaskStatus.valueOf(taskEntity.status())
        );
    }


}

