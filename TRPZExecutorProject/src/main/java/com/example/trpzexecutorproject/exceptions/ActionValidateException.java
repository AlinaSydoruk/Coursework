package com.example.trpzexecutorproject.exceptions;

public class ActionValidateException extends RuntimeException{
    public ActionValidateException() {
    }

    public ActionValidateException(String message) {
        super(message);
    }

    public ActionValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionValidateException(Throwable cause) {
        super(cause);
    }

    public ActionValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
