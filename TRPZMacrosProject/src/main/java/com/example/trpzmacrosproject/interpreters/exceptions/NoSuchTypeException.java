package com.example.trpzmacrosproject.interpreters.exceptions;

public class NoSuchTypeException extends RuntimeException{
    public NoSuchTypeException() {
    }

    public NoSuchTypeException(String message) {
        super(message);
    }

    public NoSuchTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTypeException(Throwable cause) {
        super(cause);
    }

    public NoSuchTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
