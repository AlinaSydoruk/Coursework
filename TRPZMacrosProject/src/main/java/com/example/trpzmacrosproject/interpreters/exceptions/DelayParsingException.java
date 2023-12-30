package com.example.trpzmacrosproject.interpreters.exceptions;

public class DelayParsingException extends RuntimeException{
    public DelayParsingException() {
    }

    public DelayParsingException(String message) {
        super(message);
    }

    public DelayParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DelayParsingException(Throwable cause) {
        super(cause);
    }

    public DelayParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
