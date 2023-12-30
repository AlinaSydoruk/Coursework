package com.example.trpzexecutorproject.exceptions;

public class ActionParsingException extends RuntimeException{
    public ActionParsingException() {
    }

    public ActionParsingException(String message) {
        super(message);
    }

    public ActionParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionParsingException(Throwable cause) {
        super(cause);
    }

    public ActionParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
