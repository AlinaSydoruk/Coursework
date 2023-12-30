package com.example.trpzexecutorproject.plugins.mouseplugin;

import com.example.trpzexecutorproject.exceptions.ActionExecutingException;
import com.example.trpzexecutorproject.plugins.keyplugin.KeyUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MouseExecutor {
    public void execute(JSONObject jsonObject) throws AWTException, InterruptedException {
        System.setProperty("java.awt.headless", "false");
        Robot robot = new Robot();

        Optional<Integer> clickOpt = jsonObject.has("click") ?
                MouseUtil.getCodeByName(jsonObject.getString("click"))
                : Optional.empty();

        Optional<String> positionOpt = jsonObject.has("position") ?
                Optional.of(jsonObject.getString("position"))
                : Optional.empty();

        clickOpt.ifPresent(robot::mousePress);

        if (positionOpt.isPresent()) {
            String position = positionOpt.get();

            Point currentPosition = MouseInfo.getPointerInfo().getLocation();
            int currentX = currentPosition.x;
            int currentY = currentPosition.y;


            String[] parts = position.split("\\s+");
            int newX = getMousePosition(parts[0], currentX);
            int newY = getMousePosition(parts[1], currentY);

            robot.mouseMove(newX, newY);
        }
        clickOpt.ifPresent(robot::mouseRelease);
    }


    private int getMousePosition(String condition, int currentPos) {
        if (condition.endsWith("*")) {
            condition = condition.replace("*", "").trim();
            if (!condition.isEmpty()) {
               currentPos += Integer.parseInt(condition);
               return currentPos;
            }
            return currentPos;
        }
        return Integer.parseInt(condition.trim());
    }
}


