package com.example.trpzexecutorproject.exceptions;

public class ActionExecutingException extends RuntimeException{
    public ActionExecutingException() {
    }

    public ActionExecutingException(String message) {
        super(message);
    }

    public ActionExecutingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionExecutingException(Throwable cause) {
        super(cause);
    }

    public ActionExecutingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
