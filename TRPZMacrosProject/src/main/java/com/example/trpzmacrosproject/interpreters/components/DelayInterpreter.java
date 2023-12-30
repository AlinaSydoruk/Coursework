package com.example.trpzmacrosproject.interpreters.components;

import com.example.trpzmacrosproject.interpreters.exceptions.DelayParsingException;
import com.example.trpzmacrosproject.scripts.ScriptDelay;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DelayInterpreter implements JsonInterpreter<ScriptDelay> {
    /**
     * Отримання об'єкта ScriptDelay із JSON-об'єкта
     * @param script JSON-об'єкт, що представляє затримку
     * @return ScriptDelay об'єкт, що містить інформацію про затримку
     */
    @Override
    public ScriptDelay interpret(JSONObject script) {
        // Створення нового об'єкта ScriptDelay
        ScriptDelay result = new ScriptDelay();

        // Встановлення годин, якщо вони присутні в JSON-об'єкті
        if (script.has("hours")) {
            result.setHours(getInt(script, "hours"));
        }
        // Встановлення хвилин, якщо вони присутні в JSON-об'єкт
        if (script.has("minutes")) {
            result.setMinutes(getInt(script, "minutes"));
        }
        // Встановлення секунд, якщо вони присутні в JSON-об'єкті
        if (script.has("seconds")) {
            result.setSeconds(getInt(script, "seconds"));
        }
        return result;
    }

    /**
     * Отримання цілочисельного значення з JSON-об'єкта за заданим ім'ям
     * @param script JSON-об'єкт
     * @param name ім'я поля
     * @return int цілочисельне значення поля
     * @throws DelayParsingException виняток, якщо не вдається здійснити парсинг значення
     */
    private int getInt(JSONObject script, String name) throws DelayParsingException {
        try {
            // Отримання цілочисельного значення за заданим ім'ям
            return script.getInt(name);
        } catch (Exception e) {
            throw new DelayParsingException("Can't parse delay: " + e.getMessage(), e);
        }
    }



}
