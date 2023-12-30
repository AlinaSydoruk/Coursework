package com.example.trpzexecutorproject.plugins.mouseplugin;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Optional;

@Component
public class MouseValidator {
    public void validate(JSONObject jsonObject) throws AWTException, InterruptedException {
        Optional<Integer> clickOpt = jsonObject.has("click") ?
                MouseUtil.getCodeByName(jsonObject.getString("click"))
                : Optional.empty();

        Optional<String> positionOpt = jsonObject.has("position") ?
                Optional.of(jsonObject.getString("position"))
                : Optional.empty();

        if (clickOpt.isEmpty() && positionOpt.isEmpty()){
            throw new IllegalArgumentException("There is must be at least 1 argument, found 0");
        }

        if (positionOpt.isPresent()) {
            String position = positionOpt.get();
            String[] parts = position.split("\\s+");
            validateMousePosition(parts[0]);
            validateMousePosition(parts[1]);
        }

    }


    private void validateMousePosition(String condition) {
        if (condition.endsWith("*")) {
            condition = condition.replace("*", "").trim();
            if (!condition.isEmpty()) {
               Integer.parseInt(condition);
                return;
            }
            return;
        }
        Integer.parseInt(condition.trim());
        return;
    }
}
