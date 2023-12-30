package com.example.trpzmacrosproject.interpreters;

import com.example.trpzmacrosproject.events.Event;
import com.example.trpzmacrosproject.interpreters.components.ActionsInterpreter;
import com.example.trpzmacrosproject.interpreters.components.DelayInterpreter;
import com.example.trpzmacrosproject.interpreters.components.EventInterpreter;
import com.example.trpzmacrosproject.interpreters.components.RepeatInterpreter;
import com.example.trpzmacrosproject.interpreters.exceptions.ParsingJsonException;
import com.example.trpzmacrosproject.scripts.Script;
import com.example.trpzmacrosproject.scripts.ScriptActions;
import com.example.trpzmacrosproject.scripts.ScriptDelay;
import com.example.trpzmacrosproject.scripts.ScriptRepeat;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.util.List;

@Component
@AllArgsConstructor
public class ScriptCreator {
    EventInterpreter eventInterpreter;
    DelayInterpreter delayInterpreter;
    RepeatInterpreter repeatInterpreter;
    ActionsInterpreter actionsInterpreter;

    public Script getScript(File scriptFile) {
        try {
            JSONObject root = new JSONObject(new JSONTokener(new FileReader(scriptFile)));
            //Обробка дій
            Script result = new Script(getScriptActions(root));

            // Обробка повторень
            boolean hasRepeat = false;

            if (root.has("repeat")) {
                result.setRepeat(getScriptRepeat(root));
                hasRepeat = true;
            }

            // Обробка івентів
            if (!hasRepeat) {
                result.addAllEvents(getEvents(root));
            }

            // Обробка кількості повторень
            if (root.has("repeatAmount")) {
                int repeatAmount = root.getInt("repeatAmount");
                result.setRepeatAmount(repeatAmount);
            }

            // Обробка затримки
            if (root.has("delay")) {
                result.setDelay(getScriptDelay(root));
            }

            return result;
        } catch (Exception e) {
            throw new ParsingJsonException(String.format("Exception in parsing file \"%s\": %s",
                    scriptFile.getName(), e.getMessage()), e);
        }
    }

    private ScriptDelay getScriptDelay(JSONObject root) {
        JSONObject jsonObject = root.getJSONObject("delay");
        return delayInterpreter.interpret(jsonObject);
    }

    private List<Event> getEvents(JSONObject root) {
        JSONArray eventsJson = root.getJSONArray("conditions");
        return eventInterpreter.getEvents(eventsJson);
    }

    private ScriptRepeat getScriptRepeat(JSONObject root) {
        JSONObject jsonObject = root.getJSONObject("repeat");
        return repeatInterpreter.interpret(jsonObject);
    }

    private ScriptActions getScriptActions(JSONObject root) {
        JSONArray actionsJson = root.getJSONArray("actions");
        return actionsInterpreter.getScriptActions(actionsJson);
    }


}
