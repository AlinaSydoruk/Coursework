package com.example.trpzmacrosproject.interpreters.exceptions;

public class NoSuchArgumentException extends RuntimeException{
    public NoSuchArgumentException() {
    }

    public NoSuchArgumentException(String message) {
        super(message);
    }

    public NoSuchArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchArgumentException(Throwable cause) {
        super(cause);
    }

    public NoSuchArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
