package com.example.trpzmacrosproject.scripts;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@NoArgsConstructor
@Setter
public class ScriptDelay {
    /** затримка, скільки годин перед виконанням*/
    int hours = 0;
    /** затримка, скільки хвилин перед виконанням*/
    int minutes = 0;
    /** затримка, скільки секунд перед виконанням*/
    int seconds = 1;

    /**
     * Дає дату, коли треба виконати завдання.
     * Якщо ніякої затримки немає, то автоматично виставляється затримка в 1 секунду
     * @return дата виконання
     */
    public LocalDateTime getTimeToExecute() {
        return LocalDateTime.now().plusSeconds(seconds).plusMinutes(minutes).plusHours(hours);
    }

    /**
     * Додає години до затримки
     * @param hours години (>=0)
     */
    public void setHours(int hours) {
        if (hours <0){
            throw new IllegalArgumentException("Hours can't be <0");
        }
        this.hours = hours;
    }

    /**
     * Додає хвилини до затримки
     * @param minutes хвилини (>=0)
     */
    public void setMinutes(int minutes) {
        if (minutes <0){
            throw new IllegalArgumentException("Minutes can't be <0");
        }
        this.minutes = minutes;
    }

    /**
     * Додає секунди до затримки. Якщо години і хвилини =0, і секунди =0,
     * то буде викинуто IllegalArgumentException
     * @param seconds секунди для затримки (>=0)
     */
    public void setSeconds(int seconds) {
        if (seconds <0){
            throw new IllegalArgumentException("Seconds can't be <0");
        }
        if (hours == minutes && minutes == seconds && seconds == 0) {
            throw new IllegalArgumentException("Seconds can't be 0 while hours and minutes also 0");
        }
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ScriptDelay{");
        boolean isFirstElement = true;
        if (hours != 0) {
            builder.append("hour=").append(hours);
            isFirstElement = false;
        }
        if (minutes != 0) {
            if (!isFirstElement) {
                builder.append(", ");
            }
            builder.append("min=").append(minutes);
            isFirstElement = false;
        }
        if (seconds != 0) {
            if (!isFirstElement) {
                builder.append(", ");
            }
            builder.append("sec=").append(seconds);
        }
        builder.append('}');
        return builder.toString();
    }
}
