package com.example.trpzmacrosproject.executor;

import com.example.trpzmacrosproject.scripts.ScriptActions;

public interface TaskExecutor {

    void execute(ScriptActions scriptAction);

    void validate(ScriptActions scriptAction);

    boolean isAlive();
}
