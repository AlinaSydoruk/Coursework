package com.example.trpzmacrosproject.validators.key;

import com.example.trpzmacrosproject.events.util.KeyUtil;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class KeyHoldingValidator implements KeyValidator{
    Set<Integer> holdingKeys = new HashSet<>();

    @Override
    public boolean validate(int key){
        return holdingKeys.contains(key);
    }

    public void setKeyHolding(int key){
        holdingKeys.add(key);
    }

    public void resetKey(int key){
        holdingKeys.remove(key);
    }

    @Override
    public String toString() {
        String keysString = holdingKeys.stream()
                .map(key -> KeyUtil.getNameByCode(key).orElse("Unresolved"))
                .collect(Collectors.joining(", "));
        return "KeyHoldingValidator{" +
                "holdingKeys=" + (keysString.length() == 0 ? "empty" : keysString) +
                '}';
    }
}
