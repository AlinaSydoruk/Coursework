package com.example.trpzmacrosproject.events;

import com.example.trpzmacrosproject.events.Event;
import com.example.trpzmacrosproject.events.util.KeyUtil;
import com.example.trpzmacrosproject.events.util.MouseUtil;
import com.example.trpzmacrosproject.events.util.MouseWheelUtil;
import com.example.trpzmacrosproject.validators.key.KeyHoldingValidator;
import com.example.trpzmacrosproject.validators.mouse.MouseValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MouseEvent extends Event {
    MouseValidator validator;
    int buttonAction;

    @Override
    public boolean isDone() {
        return validator.validate(buttonAction);
    }

    @Override
    public String toString() {
        return "MouseEvent{" +
                "buttonAction=" + MouseUtil.getNameByCode(buttonAction)
                .orElseGet(() -> MouseWheelUtil.getDirectionByCode(buttonAction)
                        .map(direction -> "WHEEL_" + direction)
                        .orElse("Unresolved")) +
                ", validator=" + validator +
                '}';
    }
}
