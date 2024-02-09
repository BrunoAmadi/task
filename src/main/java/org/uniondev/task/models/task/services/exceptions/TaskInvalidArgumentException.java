package org.uniondev.task.models.task.services.exceptions;

public class TaskInvalidArgumentException extends RuntimeException {
    public TaskInvalidArgumentException(String message) {
        super(message);
    }
}
