package com.example.trpzmacrosproject.events.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MouseUtil {
    private static final Map<Integer, String> keys = new HashMap<>();

    public static Optional<String> getNameByCode(int code) {
        String result = keys.get(code);
        return result == null ? Optional.empty() : Optional.of(result);
    }

    public static Optional<Integer> getCodeByName(String name) {
        for (Map.Entry<Integer, String> entry : keys.entrySet()) {
            if (entry.getValue().equals(name)) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }


    static {
        keys.put(1, "LEFT");
        keys.put(2, "RIGHT");
        keys.put(16, "MIDDLE");
        keys.put(32, "BUTTON_X1");
        keys.put(64, "BUTTON_X2");

    }
}
