package com.devsenior.fBeltran.reservation.exception;

/**
 * Exception thrown when a requested resource (e.g. reservation) is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Creates a new exception with the given message.
     *
     * @param message the detail message (may be null)
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the given message and cause.
     *
     * @param message the detail message (may be null)
     * @param cause   the cause (may be null)
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
