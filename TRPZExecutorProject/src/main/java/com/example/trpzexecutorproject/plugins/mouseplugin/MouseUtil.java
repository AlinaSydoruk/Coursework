package com.example.trpzexecutorproject.plugins.mouseplugin;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MouseUtil {
    private static final Map<Integer, String> keys = new HashMap<>();

    public static Optional<String> getNameByCode(int code){
        String result = keys.get(code);
        return result == null ? Optional.empty() : Optional.of(result);
    }

    public static Optional<Integer> getCodeByName(String name){
        for (Map.Entry<Integer, String> entry : keys.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(name)) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }



    static {
        keys.put(InputEvent.getMaskForButton(MouseEvent.BUTTON1), "LBUTTON");
        keys.put(InputEvent.getMaskForButton(MouseEvent.BUTTON3), "RBUTTON");
        keys.put(InputEvent.getMaskForButton(MouseEvent.BUTTON2), "MBUTTON");
    }
}
