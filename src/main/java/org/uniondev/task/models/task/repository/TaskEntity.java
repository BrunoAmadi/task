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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_task")
    public UUID id;

    @Column(name = "nm_title")
    public String title;

    @Column(name = "ds_description")
    public String description;

    @Column(name = "st_status")
    public TaskStatus status;

    @Column(name = "dt_created")
    public Instant createdAt;

    @Column(name = "dt_updated")
    public Instant updatedAt;


    public TaskEntity() {

    }


    public UUID id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public TaskStatus status() {
        return status;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public TaskEntity(UUID id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }



    public static TaskEntity from(Task task) {
      return new TaskEntity(
              task.id(),
              task.title(),
              task.description(),
              task.status()
      );
    }


}

