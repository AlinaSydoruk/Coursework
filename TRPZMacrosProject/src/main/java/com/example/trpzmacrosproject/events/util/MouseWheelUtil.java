package com.example.trpzmacrosproject.events.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MouseWheelUtil {

    public static Optional<String> getDirectionByCode(int code){
        Map<Integer, String> keys = getMouseWheelMap();
        String result = keys.get(code);
        return result == null ? Optional.empty() : Optional.of(result);
    }

    public static Optional<Integer> getCodeByDirection(String direction){
        Map<Integer, String> keys = getMouseWheelMap();
        for (Map.Entry<Integer, String> entry : keys.entrySet()) {
            if (entry.getValue().equals(direction)) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }

    public static Map<Integer, String> getMouseWheelMap(){
        Map<Integer, String> keys = new HashMap<>();
        keys.put(120, "UP");
        keys.put(-120, "DOWN");
        return keys;
    }
}
