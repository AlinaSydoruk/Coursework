package com.example.trpzmacrosproject.configurations;

import com.example.trpzmacrosproject.validators.AfkValidator;
import com.example.trpzmacrosproject.validators.key.KeyHoldingValidator;
import com.example.trpzmacrosproject.validators.key.KeyPressedValidator;
import com.example.trpzmacrosproject.services.ScriptService;
import com.example.trpzmacrosproject.validators.mouse.MousePressedValidator;
import com.example.trpzmacrosproject.validators.mouse.MouseWheelValidator;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import lc.kra.system.mouse.event.GlobalMouseListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyAndMouseHookConfiguration {

    //Перехоплювання з клавіатури
    @Bean
    public GlobalKeyboardHook keyListener(KeyPressedValidator keyPressedValidator,
                                          KeyHoldingValidator keyHoldingValidator,
                                          ScriptService scriptService) {
        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);
        keyboardHook.addKeyListener(new GlobalKeyListener() {
            @Override
            public void keyPressed(GlobalKeyEvent event) {
                keyPressedValidator.setKeyPressed(event.getVirtualKeyCode());
                keyHoldingValidator.setKeyHolding(event.getVirtualKeyCode());
                scriptService.checkAllScripts();
             //  System.out.println("Клавіша була нажата: " + event.getVirtualKeyCode());
            }

            @Override
            public void keyReleased(GlobalKeyEvent event) {
                keyPressedValidator.resetKey();
                keyHoldingValidator.resetKey(event.getVirtualKeyCode());
             // System.out.println("Клавіша була відпущена: " + event.getVirtualKeyCode());
            }
        });
        return keyboardHook;
    }


    //Перехоплювання миші
    @Bean
    public GlobalMouseHook mouseListener(MousePressedValidator mousePressedValidator,
                                         MouseWheelValidator mouseWheelValidator,
                                         ScriptService scriptService, AfkValidator afkValidator) {
        GlobalMouseHook mouseHook = new GlobalMouseHook(true);

        mouseHook.addMouseListener(new GlobalMouseListener() {
            @Override
            public void mousePressed(GlobalMouseEvent event) {
               mousePressedValidator.setKeyPressed(event.getButton());
               scriptService.checkAllScripts();
             // System.out.println("Миша натиснута: " + event);
            }

            @Override
            public void mouseReleased(GlobalMouseEvent event) {
               mousePressedValidator.resetKey();
              // System.out.println("Миша відпущена: " + event);
            }

            @Override
            public void mouseMoved(GlobalMouseEvent event) {
                afkValidator.updateLastActivity();
             //  System.out.println("Миша двигалась: " + event.getButtons());
            }

            @Override
            public void mouseWheel(GlobalMouseEvent event) {
                mouseWheelValidator.setKeyPressed(event.getDelta());
                scriptService.checkAllScripts();
                mouseWheelValidator.resetKey();
               // System.out.println("Миша колесико: " + event);
            }

        });
        return mouseHook;
    }


}
