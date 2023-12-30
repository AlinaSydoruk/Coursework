package com.example.trpzmacrosproject.interpreters.factories;

import com.example.trpzmacrosproject.events.AfkEvent;
import com.example.trpzmacrosproject.events.KeyEvent;
import com.example.trpzmacrosproject.events.util.KeyUtil;
import com.example.trpzmacrosproject.events.MouseEvent;
import com.example.trpzmacrosproject.events.util.MouseUtil;
import com.example.trpzmacrosproject.events.util.MouseWheelUtil;
import com.example.trpzmacrosproject.interpreters.exceptions.EventParsingException;
import com.example.trpzmacrosproject.interpreters.exceptions.NoSuchArgumentException;
import com.example.trpzmacrosproject.validators.AfkValidator;
import com.example.trpzmacrosproject.validators.key.KeyHoldingValidator;
import com.example.trpzmacrosproject.validators.key.KeyPressedValidator;
import com.example.trpzmacrosproject.validators.mouse.MousePressedValidator;
import com.example.trpzmacrosproject.validators.mouse.MouseWheelValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EventAbstractFactoryImpl implements EventAbstractFactory {
    private AfkValidator afkValidator;
    private KeyPressedValidator keyPressedValidator;
    private KeyHoldingValidator keyHoldingValidator;
    private MousePressedValidator mousePressedValidator;
    private MouseWheelValidator mouseWheelValidator;

    @Override
    public AfkEvent getAfkEvent(Map<String, String> args) {
        try {
            String timeStr = getArgument(args, "time");
            int time = Integer.parseInt(timeStr);
            return new AfkEvent(afkValidator, time);
        } catch (Exception e){
            throw new EventParsingException("Error when create AFKEvent: "+ e.getMessage(), e);
        }
    }

    @Override
    public KeyEvent getKeyPressedEvent(Map<String, String> args) {
        try {
            String strKey = getArgument(args, "key");
            return new KeyEvent(keyPressedValidator, getKeyByName(strKey));
        } catch (Exception e){
            throw new EventParsingException("Error when create KeyPressedEvent: "+ e.getMessage(), e);
        }
    }

    @Override
    public KeyEvent getKeyHoldingEvent(Map<String, String> args) {
        try {
            String strKey = getArgument(args, "key");
            return new KeyEvent(keyHoldingValidator, getKeyByName(strKey));
        } catch (Exception e){
            throw new EventParsingException("Error when create KeyHoldingEvent: "+ e.getMessage(), e);
        }
    }

    @Override
    public MouseEvent getMousePressedEvent(Map<String, String> args) {
        try {
            String strKey = getArgument(args, "key");
            Optional<Integer> optKey = MouseUtil.getCodeByName(strKey.toUpperCase(Locale.ROOT));
            if (optKey.isEmpty()){
                throw new IllegalArgumentException("No such key: "+ strKey);
            }
            return new MouseEvent(mousePressedValidator, optKey.get());
        } catch (Exception e){
            throw new EventParsingException("Error when create MousePressedEvent: "+ e.getMessage(), e);
        }
    }

    @Override
    public MouseEvent getMouseWheelEvent(Map<String, String> args) {
        try {
            String strDir = getArgument(args, "direction");
            Optional<Integer> optDir = MouseWheelUtil.getCodeByDirection(strDir.toUpperCase(Locale.ROOT));
            if (optDir.isEmpty()){
                throw new IllegalArgumentException("No such direction: "+ strDir);
            }
            return new MouseEvent(mouseWheelValidator, optDir.get());
        } catch (Exception e){
            throw new EventParsingException("Error when create MouseWheelEvent: "+ e.getMessage(), e);
        }
    }


    //======= Допоміжні методи ======
    private int getKeyByName(String name){
        Optional<Integer> optKey = KeyUtil.getCodeByName(name.toUpperCase(Locale.ROOT));
        if (optKey.isEmpty()){
            throw new IllegalArgumentException("No such key: "+ name);
        }
        return optKey.get();
    }

    private String getArgument(Map<String, String> map, String argument) throws NoSuchArgumentException {
        String result = map.get(argument);
        if (result == null) {
            throw new NoSuchArgumentException("argument \"" + argument + "\" is empty");
        }
        return result;
    }

}
