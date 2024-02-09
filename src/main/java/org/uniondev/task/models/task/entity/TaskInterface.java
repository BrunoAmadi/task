package org.uniondev.task.models.task.entity;
import org.uniondev.task.models.task.enums.TaskStatus;
import java.time.Instant;
import java.util.UUID;

public interface TaskInterface {

    UUID id();
    String title();
    String description();
    TaskStatus status();

    void updateTitle(String title);
    void updateDescription(String description);
    void updateStatus(String status);

}
