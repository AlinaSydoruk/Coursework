package com.example.trpzmacrosproject.validators;

import com.example.trpzmacrosproject.events.KeyEvent;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AfkValidator {
    private LocalDateTime lastActivityTime= LocalDateTime.now();

    public void updateLastActivity() {
        lastActivityTime = LocalDateTime.now();
    }

    public boolean isUserEnoughAfk(int minutes) {
        return Duration.between(lastActivityTime, LocalDateTime.now()).toMinutes() >= minutes;
    }

}
