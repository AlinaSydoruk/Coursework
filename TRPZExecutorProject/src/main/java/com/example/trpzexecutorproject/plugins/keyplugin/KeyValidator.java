package com.example.trpzexecutorproject.plugins.keyplugin;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class KeyValidator {
    public void validate(JSONObject jsonObject) {
        String keysStr = jsonObject.getString("keys");
        if (keysStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Keys is empty");
        }
        if (jsonObject.has("simultaneously")) {
            jsonObject.getBoolean("simultaneously");
        }

        String[] keys = keysStr.split("\\s");
        for (String key : keys) {
            if (key == null || key.trim().isEmpty()) {
                throw new IllegalArgumentException("Illegal keys: " + keysStr);
            }
            if (KeyUtil.getCodeByName(key).isEmpty()){
                throw new IllegalArgumentException("Invalid key: " + key);
            }
        }
    }
}
