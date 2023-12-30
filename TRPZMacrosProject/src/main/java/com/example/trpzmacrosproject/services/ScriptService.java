package com.example.trpzmacrosproject.services;

import com.example.trpzmacrosproject.TrpzMacrosProjectApplication;
import com.example.trpzmacrosproject.executor.TaskExecutor;
import com.example.trpzmacrosproject.scripts.ScriptDelay;
import com.example.trpzmacrosproject.scripts.exceptions.ScriptDontHaveRepeatException;
import com.example.trpzmacrosproject.scripts.Script;
import com.example.trpzmacrosproject.scripts.ScriptHolder;
import com.example.trpzmacrosproject.sheduled.ScheduledTasksService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Component
@AllArgsConstructor
public class ScriptService {
    private static final Logger logger = LoggerFactory.getLogger(TrpzMacrosProjectApplication.class);
    private final ScriptHolder scriptHolder;
    private final ScheduledTasksService scheduledTasksService;
    TaskExecutor taskExecutor;

    /**
     * Додає скрипт до ScriptHolder. Додає як звичайні скрипти,
     * так і скрипти, які повторюються.
     * @param script
     */
    public void addScriptToHolder(Script script) {
        if (script.getRepeat().isPresent()) {
            addRepeatableScript(script);
        } else{
        scriptHolder.addScript(script);
        }
        logger.info("Added Script to ScriptHolder: "+ script);
    }

    public void removeScriptToHolder(Script script) {
        if (script.getRepeat().isPresent()) {
            removeRepeatableScript(script);
        } else {
            scriptHolder.removeScript(script);
        }
        logger.info("Removed Script to ScriptHolder: "+ script);
    }

    /**
     * Перевіряє всі скрипти на те, чи були виконані всі умови
     */
    public synchronized void checkAllScripts() {
        List<Script> madeScripts = scriptHolder.getScripts().stream().filter(Script::doesAllEventsDone).toList();
        for (Script s : madeScripts) {
            executeScriptWithDelay(s);
        }
    }

    /**
     * Виконує скрипт, враховуючи затримку. Якщо затримки немає, скрипт виконається одразу.
     * @param script скрипт для виконання
     */
    public void executeScriptWithDelay(Script script) {
        if (script.getDelay().isEmpty()) {
            executeScript(script);
            return;
        }
        ScriptDelay delay = script.getDelay().get();
        LocalDateTime timeToExecute = delay.getTimeToExecute();
        scheduledTasksService.delayScheduledTask(() -> this.executeScript(script), timeToExecute);
    }

    /**
     * Ставить скрипт на повторення. Також враховує затримку скрипта
     * @param script скрипт для виконання
     */
    private ScheduledFuture<?> setRepeatableScript(Script script) {
        if (script.getRepeat().isEmpty()) {
            throw new ScriptDontHaveRepeatException("This script doesn't have repeat!", script);
        }
        CronTrigger cronTrigger = script.getRepeat().get().getCronTrigger();
        return scheduledTasksService.repeatableScheduledTask(() -> this.executeScriptWithDelay(script), cronTrigger);
    }


    public synchronized void executeScript(Script script) {
        try {
            logger.info(String.format("Execute script: %s", script));
            taskExecutor.execute(script.getActions());
        } catch (Exception e){
            logger.error(String.format("Error while executing script %s: %s", script, e.getMessage()), e);
        } finally {
            //Якщо скрипт без повтору, то видалення скрипту
            if (script.minusRepeatAmount() == 0) {
                removeScriptToHolder(script);
            }
        }
    }

    private void addRepeatableScript(Script script) {
        scriptHolder.addRepeatableScript(script, setRepeatableScript(script));
    }

    private void removeRepeatableScript(Script script) {
        scriptHolder.getScheduledFutureByScript(script).cancel(true);
        scriptHolder.removeRepeatableScript(script);
    }

}
