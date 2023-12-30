package com.example.trpzmacrosproject.interpreters.components;

import com.example.trpzmacrosproject.interpreters.exceptions.DelayParsingException;
import com.example.trpzmacrosproject.scripts.ScriptRepeat;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class RepeatInterpreter implements JsonInterpreter<ScriptRepeat> {
    /**
     * Отримання об'єкта ScriptRepeat із JSON-об'єкта
     * @param script JSON-об'єкт, що представляє повторення
     * @return ScriptRepeat об'єкт, що містить інформацію про повторення
     */
    @Override
    public ScriptRepeat interpret(JSONObject script) {
        // Створення нового об'єкта ScriptRepeat
        ScriptRepeat result = new ScriptRepeat();

        // Встановлення значень, якщо вони присутні в JSON-об'єкті
        setIfPresent(script, "second", result::setExactSecond);
        setIfPresent(script, "minutes", result::setExactMinute);
        setIfPresent(script, "hours", result::setExactHour);
        setIfPresent(script, "dayOfMonth", result::setExactDayOfMonth);
        setIfPresent(script, "month", result::setExactMonth);
        setIfPresent(script, "dayOfWeek", result::setExactDayOfWeek);
        return result;
    }

    /**
     * Встановлення значення, якщо воно присутнє в JSON-об'єкті
     *
     * @param script JSON-об'єкт
     * @param key    ключ поля
     * @param setter функція-встановлювач для відповідного поля в ScriptRepeat
     */
    private void setIfPresent(JSONObject script, String key, Consumer<Integer> setter) {

        try {
            // Перевірка, чи присутнє значення за ключем у JSON-об'єкті
            if (script.has(key)) {
                // Якщо так, викликається функція-встановлювач для встановлення значення
                setter.accept(script.getInt(key));
            }
        } catch (Exception e) {
            throw new DelayParsingException("Can't parse ScriptRepeat: " + e.getMessage(), e);
        }
    }


}