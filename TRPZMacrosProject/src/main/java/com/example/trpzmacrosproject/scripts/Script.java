package com.example.trpzmacrosproject.scripts;

import com.example.trpzmacrosproject.events.Event;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class Script {
    private static int lastId = 1;
    private int id;
    private List<Event> events = new LinkedList<>();
    private ScriptDelay delay;
    private ScriptRepeat repeat;
    private ScriptActions action;
    /** Скільки раз повторюватиметься скрипт */
    private int repeatAmount = 1;


    public Script(ScriptActions action, Event... events) {
        this.action = action;
        this.id = lastId++;
        this.events.addAll(List.of(events));
    }

    public synchronized boolean doesAllEventsDone(){
        return events.stream().allMatch(Event::isDone);
    }

    public Optional<ScriptDelay>  getDelay() {
        return Optional.ofNullable(delay) ;
    }

    public Optional<ScriptRepeat> getRepeat() {
        return Optional.ofNullable(repeat)  ;
    }

    public ScriptActions getActions() {
        return action;
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void addAllEvents(List<Event> events){
        events.forEach(this::addEvent);
    }

    /**
     * Віднімає 1 використання і повертає відняте значення. Працює тільки тоді,
     * коли кількість використань, в інших випадках поверне від'ємне число.
     * @return <0, коли вимкнено, 0, коли закінчились виклики, >0 коли є ще виклики
     */

    public int minusRepeatAmount(){
        if (repeatAmount > 0){
            repeatAmount--;
        }
        return repeatAmount;
    }

    /**
     * Виставляє кількість повторень (за замовчуванням 1)
     * @param amount кількість повторень.
     *  0 - скрипт виключений
     *  <0 - скрипт завжди повторюватиметься
     *  >0 - кількість повторень
     */
    public void setRepeatAmount(int amount) {
        this.repeatAmount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Script script = (Script) o;

        return id == script.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Script{" +
                "id=" + id +
                ", events=" + events +
                (delay == null? "" : ", delay=" + delay)+
                (repeat == null? "" : ", repeat=" + repeat)+
                ", repeatAmount=" + repeatAmount +
                '}';
    }
}
