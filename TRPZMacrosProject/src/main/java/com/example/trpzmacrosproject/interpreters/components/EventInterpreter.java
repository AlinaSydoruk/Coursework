package com.example.trpzmacrosproject.interpreters.components;

import com.example.trpzmacrosproject.events.Event;
import com.example.trpzmacrosproject.interpreters.exceptions.EventParsingException;
import com.example.trpzmacrosproject.interpreters.exceptions.NoSuchTypeException;
import com.example.trpzmacrosproject.interpreters.factories.EventAbstractFactory;
import com.example.trpzmacrosproject.interpreters.factories.EventAbstractFactoryImpl;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class EventInterpreter implements JsonInterpreter<Event> {
    EventAbstractFactory eventAbstractFactory;

    public List<Event> getEvents(JSONArray events){
        List<Event> result = new LinkedList<>();
        for (int i = 0; i < events.length(); i++) {
            result.add(interpret(events.getJSONObject(i)));
        }
        return result;
    }

    @Override
    public Event interpret(JSONObject  event) {
        Map<String, String> values = convertToMap(event);
        try{
            return getEvent(values);
        } catch (JSONException e){
            throw new EventParsingException("Can't create event "+ event+ ": "+ e.getMessage());
        }
    }

    /**
     * Віддає створений евент на основі аргументів
     * @param arguments аргументи
     * @return евент
     */
    public Event getEvent(Map<String, String> arguments) {
        String type = arguments.get("type");
        if (type == null) {
            throw new NoSuchTypeException("type is empty");
        }
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "AFK" -> eventAbstractFactory.getAfkEvent(arguments);
            case "KEY-PRESSED" -> eventAbstractFactory.getKeyPressedEvent(arguments);
            case "KEY-HOLDING" -> eventAbstractFactory.getKeyHoldingEvent(arguments);
            case "MOUSE-PRESSED" -> eventAbstractFactory.getMousePressedEvent(arguments);
            case "MOUSE-WHEEL-SCROLLING" -> eventAbstractFactory.getMouseWheelEvent(arguments);
            default -> throw new NoSuchTypeException("Illegal event type: "+ type);
        };
    }


    private Map<String, String> convertToMap(JSONObject jsonObject) {
        Map<String, String> map = new HashMap<>();
        jsonObject.keys().forEachRemaining(key -> {
            Object value = jsonObject.get(key);
            if (value == null){
                throw new EventParsingException("Can't convert this argument: " + key);
            }
            map.put(key, value.toString());
        });
        return map;
    }





/*
    public Event getEvent(JSONObject event){
        Map<String, String> values = convertToMap(event);
        try{
        return getEvent(values);
        } catch (JSONException e){
            throw new EventParsingException("Can't create event "+ event+ ": "+ e.getMessage());
        }
    }*/
}
