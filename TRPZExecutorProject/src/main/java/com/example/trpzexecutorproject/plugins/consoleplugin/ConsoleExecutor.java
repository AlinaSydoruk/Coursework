package com.example.trpzexecutorproject.plugins.consoleplugin;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsoleExecutor {

    public void execute(JSONObject jsonObject) {
        String text = jsonObject.getString("text");
        System.out.println("\u001B[33m" + text + "\u001B[0m");
    }
}
