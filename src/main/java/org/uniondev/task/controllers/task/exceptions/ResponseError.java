package org.uniondev.task.controllers.task.exceptions;

import java.time.Instant;
import java.util.Date;

public record ResponseError(Instant timestamp, int status, String message, String path) {


}
