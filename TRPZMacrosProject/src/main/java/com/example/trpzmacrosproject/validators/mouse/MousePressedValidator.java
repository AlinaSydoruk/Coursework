package com.example.trpzmacrosproject.validators.mouse;

import com.example.trpzmacrosproject.events.util.MouseUtil;
import org.springframework.stereotype.Component;

@Component
public class MousePressedValidator implements MouseValidator{
    int virtualMouseButtonPressed =-1;

    @Override
    public boolean validate(int key){
        return key == virtualMouseButtonPressed;
    }

    public void setKeyPressed(int key){
        virtualMouseButtonPressed = key;
    }

    public void resetKey(){
        virtualMouseButtonPressed = -1;
    }

    @Override
    public String toString() {
        return "MousePressedValidator{" +
                "lastMouseButtonPressed=" +  MouseUtil.getNameByCode(virtualMouseButtonPressed).orElse("Unresolved") +
                '}';
    }
}
