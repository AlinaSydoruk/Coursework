package com.example.trpzmacrosproject.interpreters.exceptions;

public class RepeatParsingException extends RuntimeException{
    public RepeatParsingException() {
    }

    public RepeatParsingException(String message) {
        super(message);
    }

    public RepeatParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatParsingException(Throwable cause) {
        super(cause);
    }

    public RepeatParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
