package com.example.trpzexecutorproject.plugins;

import org.json.JSONObject;

import java.util.Map;
// interface Command

public interface Plugin {

    void validate(JSONObject jsonObject);

    void execute(JSONObject jsonObject);

    String getPluginName();
}
