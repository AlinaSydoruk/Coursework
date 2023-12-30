package com.example.trpzmacrosproject.interpreters.exceptions;

public class EventParsingException extends RuntimeException{
    public EventParsingException() {
    }

    public EventParsingException(String message) {
        super(message);
    }

    public EventParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventParsingException(Throwable cause) {
        super(cause);
    }

    public EventParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
