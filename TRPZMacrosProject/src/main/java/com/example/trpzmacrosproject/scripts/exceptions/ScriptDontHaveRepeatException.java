package com.example.trpzmacrosproject.scripts.exceptions;

import com.example.trpzmacrosproject.scripts.Script;

public class ScriptDontHaveRepeatException extends RuntimeException{
    Script script = null;

    public ScriptDontHaveRepeatException() {
    }
    public ScriptDontHaveRepeatException(String message, Script script) {
        super(message + ": "+ script);
        this.script = script;
    }

    public ScriptDontHaveRepeatException(String message) {
        super(message);
    }

    public ScriptDontHaveRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScriptDontHaveRepeatException(Throwable cause) {
        super(cause);
    }

    public ScriptDontHaveRepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
