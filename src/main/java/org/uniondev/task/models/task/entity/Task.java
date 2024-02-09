package org.uniondev.task.models.task.entity;

import org.uniondev.task.models.task.enums.TaskStatus;
import org.uniondev.task.models.task.entity.exceptions.TaskIllegalArgumentException;
import java.util.UUID;

public class Task implements TaskInterface {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;


    public Task() {
    }


    private Task(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.validate();
    }

    public Task(UUID id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.validate();
    }


    public UUID id() {
        return this.id;
    }

    public String title() {
        return this.title;
    }

    public String description() {
        return this.description;
    }

    public TaskStatus status() {
        return this.status;
    }


    public void updateAll(String title, String description, String status) {
        this.updateTitle(title);
        this.updateDescription(description);
        this.updateStatus(status);
    }


    public void updateStatus(String status) {
        this.status = TaskStatus.valueOf(status);

    }

    public void updateTitle(String title) {
        this.title = title;
        this.validateTitle();
    }


    public void updateDescription(String description) {
        this.description = description;
        this.validateDescription();
    }

    public static Task create(String title, String description) throws TaskIllegalArgumentException {
        return new Task(title, description);
    }


    private void validate() {
        this.validateTitle();
        this.validateDescription();
    }


    private void validateTitle() {
        if (this.title.length() < 3 || this.title.length() > 50) {
            throw new TaskIllegalArgumentException("Title must be between 3 and 50 characters");
        }
    }

    private void validateDescription() {
        if (this.description.length() < 3 || this.description.length() > 250) {
            throw new TaskIllegalArgumentException("Description must be between 3 and 250 characters");
        }
    }


}
