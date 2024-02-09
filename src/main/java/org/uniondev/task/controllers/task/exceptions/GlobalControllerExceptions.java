package org.uniondev.task.controllers.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.uniondev.task.models.task.services.exceptions.TaskInvalidArgumentException;
import org.uniondev.task.models.task.services.exceptions.TaskNotFoundException;
import java.time.Instant;


@ControllerAdvice
public class GlobalControllerExceptions {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ResponseError> handleTaskNotFoundException(TaskNotFoundException e, WebRequest request) {
        ResponseError error = new ResponseError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getContextPath()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(TaskInvalidArgumentException.class)
    public ResponseEntity<ResponseError> handleTaskInvalidArgumentException(TaskInvalidArgumentException e, WebRequest request) {
        ResponseError error = new ResponseError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getContextPath()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
