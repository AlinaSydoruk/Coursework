package com.example.trpzmacrosproject.sheduled;

import com.example.trpzmacrosproject.services.ScriptService;
import com.example.trpzmacrosproject.validators.AfkValidator;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@AllArgsConstructor
public class SheduledListener {
    ScriptService scriptService;

    /** Викликає перевірку всіх скриптів (перевірка на AFK користувача)  */
    @Scheduled(fixedRate = 10_000)
    public void performTask() {
        scriptService.checkAllScripts();
    }
}
