package com.example.trpzmacrosproject.validators.mouse;

import com.example.trpzmacrosproject.events.util.MouseWheelUtil;
import org.springframework.stereotype.Component;

@Component
public class MouseWheelValidator implements  MouseValidator{
    int virtualWheelDelta =-1;

    @Override
    public boolean validate(int key){
        return key == virtualWheelDelta;
    }

    public void setKeyPressed(int key){
        virtualWheelDelta = key;
    }

    public synchronized void resetKey(){
        virtualWheelDelta = -1;
    }

    @Override
    public String toString() {
        return "MouseWheelValidator{" +
                "wheelDirection=" +  MouseWheelUtil.getDirectionByCode(virtualWheelDelta).orElse("Unresolved") +
                '}';
    }
}
