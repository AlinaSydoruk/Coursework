package com.example.trpzmacrosproject.scripts;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;


@AllArgsConstructor
@Setter
public class ScriptActions {
    JSONArray actions;

    // // Метод, який повертає рядок JSON, представляючий масив дій
    public String getJsonActions(){
        return actions.toString();
    }

    @Override
    public String toString() {
        return "ScriptAction{" +
                "actions amount=" + actions.length() +
                '}';
    }
}
