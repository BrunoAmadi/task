package org.uniondev.task.models.task.entity;

import org.uniondev.task.models.task.enums.TaskStatus;
import org.uniondev.task.models.task.entity.exceptions.TaskIllegalArgumentException;

import java.time.Instant;
import java.util.UUID;

public class Task implements TaskInterface{
    private final UUID id;
    private String title;
    private  String description;
    private TaskStatus status;
    private boolean isDone;


    private Task(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.isDone = false;
        this.validate();
    }

    public static Task create(String title, String description) {
        return new Task(title, description);
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


    public boolean isDone() {
        return this.isDone;
    }

    public void updateStatus(TaskStatus status) {
        this.status = status;

        if(status == TaskStatus.DONE){
            this.isDone = true;
        }

    }

    public void updateTitle(String title) {
        this.title = title;
        this.validateTitle();
    }


    public void updateDescription(String description) {
        this.description = description;
        this.validateDescription();
    }



    private void validate(){
        this.validateTitle();
        this.validateDescription();
    }


    private void validateTitle(){
        if(this.title.length() < 3 || this.title.length() > 50){
            throw new TaskIllegalArgumentException("Title must be between 3 and 50 characters");
        }
    }

    private void validateDescription(){
        if(this.description.length() < 3 || this.description.length() > 250){
            throw new TaskIllegalArgumentException("Description must be between 3 and 250 characters");
        }
    }




}
