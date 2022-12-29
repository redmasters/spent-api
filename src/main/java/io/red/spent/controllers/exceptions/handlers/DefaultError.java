package io.red.spent.controllers.exceptions.handlers;

import java.time.Instant;

public record DefaultError(
        Instant now,
        int status,
        String defaultErrorView,
        String message,
        String requestURI
) {
}
