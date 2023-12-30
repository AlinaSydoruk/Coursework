package com.example.trpzmacrosproject.interpreters.factories;

import com.example.trpzmacrosproject.events.AfkEvent;
import com.example.trpzmacrosproject.events.KeyEvent;
import com.example.trpzmacrosproject.events.MouseEvent;

import java.util.Map;

public interface EventAbstractFactory {
    AfkEvent getAfkEvent(Map<String, String> arguments);

    KeyEvent getKeyPressedEvent(Map<String, String> arguments);

    KeyEvent getKeyHoldingEvent(Map<String, String> arguments);

    MouseEvent getMousePressedEvent(Map<String, String> arguments);

    MouseEvent getMouseWheelEvent(Map<String, String> arguments);
}
