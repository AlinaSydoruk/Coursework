package com.example.trpzmacrosproject.validators.key;

import com.example.trpzmacrosproject.events.util.KeyUtil;
import com.example.trpzmacrosproject.events.util.MouseUtil;
import org.springframework.stereotype.Component;

@Component
public class KeyPressedValidator implements KeyValidator{
    int virtualKeyCodePressed =-1;

    @Override
    public boolean validate(int key){
        return key == virtualKeyCodePressed;
    }

    public void setKeyPressed(int key){
        virtualKeyCodePressed = key;
    }

    public void resetKey(){
        virtualKeyCodePressed = -1;
    }

    @Override
    public String toString() {
        return "KeyPressedValidator{" +
                "keyPressed=" +  KeyUtil.getNameByCode(virtualKeyCodePressed).orElse("Unresolved") +
                '}';
    }
}
