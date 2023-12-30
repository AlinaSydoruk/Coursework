package com.example.trpzexecutorproject.plugins.consoleplugin;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ConsoleValidator  {
    public void validate(JSONObject jsonObject) {
        String text = jsonObject.getString("text");
        if (text.trim().isEmpty()) {
            throw new IllegalArgumentException("text is empty");
        }
    }
}
