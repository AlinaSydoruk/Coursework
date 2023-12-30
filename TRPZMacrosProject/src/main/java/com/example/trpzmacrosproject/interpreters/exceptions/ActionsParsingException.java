package com.example.trpzmacrosproject.interpreters.exceptions;

public class ActionsParsingException extends RuntimeException{
    public ActionsParsingException() {
    }

    public ActionsParsingException(String message) {
        super(message);
    }

    public ActionsParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionsParsingException(Throwable cause) {
        super(cause);
    }

    public ActionsParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
