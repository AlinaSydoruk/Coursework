package com.example.trpzmacrosproject.interpreters.exceptions;

public class ParsingJsonException extends RuntimeException{
    public ParsingJsonException() {
    }

    public ParsingJsonException(String message) {
        super(message);
    }

    public ParsingJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingJsonException(Throwable cause) {
        super(cause);
    }

    public ParsingJsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
