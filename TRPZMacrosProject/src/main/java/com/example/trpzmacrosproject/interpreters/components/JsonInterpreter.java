    package com.example.trpzmacrosproject.interpreters.components;

    import org.json.JSONObject;

    public interface JsonInterpreter<T> {

        T interpret(JSONObject json);

    }
