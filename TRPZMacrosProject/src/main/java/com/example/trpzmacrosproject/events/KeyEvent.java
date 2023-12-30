package com.example.trpzmacrosproject.events;

import com.example.trpzmacrosproject.events.util.KeyUtil;
import com.example.trpzmacrosproject.validators.key.KeyValidator;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class KeyEvent extends Event{
    KeyValidator validator;
    int key;

    @Override
    public boolean isDone() {
        return validator.validate(key);
    }

    @Override
    public String toString() {
        return "KeyEvent{" +
                "key=" + KeyUtil.getNameByCode(key).orElse("Unresolved") +
                ", validator=" + validator +
                '}';
    }

}
