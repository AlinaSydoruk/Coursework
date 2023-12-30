package com.example.trpzexecutorproject.plugins.consoleplugin;

import com.example.trpzexecutorproject.plugins.Plugin;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@ToString
@AllArgsConstructor
public class ConsolePlugin implements Plugin {

    ConsoleValidator consoleValidator;
    ConsoleExecutor consoleExecutor;

    @Override
    public void validate(JSONObject jsonObject) {
        consoleValidator.validate(jsonObject);
    }

    @Override
    public void execute(JSONObject jsonObject) {
        consoleExecutor.execute(jsonObject);
    }

    @Override
    public String getPluginName() {
        return "CONSOLE";
    }
}
