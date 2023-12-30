package com.example.trpzexecutorproject.utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONConvertor {
    public static Map<String, String> convertToStringMap(JSONObject jsonObject) {
        Map<String, String> map = new HashMap<>();

        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = jsonObject.get(key);
            map.put(key, value.toString());
        }
        return map;
    }
}
