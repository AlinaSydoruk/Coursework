package com.example.trpzmacrosproject.events;

import com.example.trpzmacrosproject.validators.AfkValidator;
import lombok.AllArgsConstructor;
import lombok.ToString;

public class AfkEvent extends Event{
    AfkValidator validator;
    int afkTimeInMinutes;

    public AfkEvent(AfkValidator validator, int afkTimeInMinutes) {
        validate(validator, afkTimeInMinutes);
        this.validator = validator;
        this.afkTimeInMinutes = afkTimeInMinutes;
    }

    private void validate(AfkValidator validator, int afkTimeInMinutes){
        if (afkTimeInMinutes <=0) {
            throw new IllegalArgumentException("time can't be <=0");
        }
        if (validator == null){
            throw new NullPointerException("validator can't be null");
        }
    }

    public boolean isDone() {
        return validator.isUserEnoughAfk(afkTimeInMinutes);
    }

    @Override
    public String toString() {
        return "AfkEvent{" +
                "afkTimeInMinutes=" + afkTimeInMinutes +
                '}';
    }
}
