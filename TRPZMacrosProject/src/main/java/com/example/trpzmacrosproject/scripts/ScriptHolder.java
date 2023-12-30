package com.example.trpzmacrosproject.scripts;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;


@Component
public class ScriptHolder {
    /** Тут зберігаються скрипти, які спрацьовують від зовнішніх події  */
    private final List<Script> scripts = new CopyOnWriteArrayList<>();

    /** Тут зберігаються скрипти, які спрацьовують регулярно, по розкладу */
    private final Map<Script, ScheduledFuture<?>> repeatableScripts = new HashMap<>();

    public void addScript(Script script) {
            scripts.add(script);
    }

    public void removeScript(Script script) {
        scripts.remove(script);
    }

    public List<Script> getScripts() {
        return Collections.unmodifiableList(scripts);
    }

    public void addRepeatableScript(Script script, ScheduledFuture<?> scheduledFuture) {
        repeatableScripts.put(script, scheduledFuture);
    }

    public void removeRepeatableScript(Script script) {
        repeatableScripts.remove(script);
    }

    public ScheduledFuture<?> getScheduledFutureByScript(Script script) {
        return repeatableScripts.get(script);
    }
}
