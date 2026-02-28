package com.devsenior.fBeltran.reservation.exception;

/**
 * Exception thrown when a business rule is violated (e.g. duplicate reservation slot, invalid cancellation).
 */
public class BusinessRuleException extends RuntimeException {

    /**
     * Creates a new exception with the given message.
     *
     * @param message the detail message (may be null)
     */
    public BusinessRuleException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the given message and cause.
     *
     * @param message the detail message (may be null)
     * @param cause   the cause (may be null)
     */
    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
