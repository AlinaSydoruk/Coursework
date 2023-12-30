package com.example.trpzmacrosproject.sheduled;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledFuture;


@Component
public class ScheduledTasksService {
    private final TaskScheduler scheduler;


    public ScheduledTasksService() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.initialize();
        this.scheduler = threadPoolTaskScheduler;
    }


    public ScheduledFuture<?> repeatableScheduledTask(Runnable action, CronTrigger cronTrigger) {
       return scheduler.schedule(action, cronTrigger);
    }

    public void delayScheduledTask(Runnable action, LocalDateTime dateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("s m H d M ?");
            String cronExpression = dateTime.format(formatter);
            scheduler.schedule(action, new CronTrigger(cronExpression));
        }
}