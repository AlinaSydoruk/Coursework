package com.example.trpzexecutorproject.controllers;
import com.example.trpzexecutorproject.service.PluginService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Анотація @RestController вказує, що цей клас є веб-контролером, який обробляє HTTP-запити.
// @RequestMapping встановлює базовий шлях для усіх ендпоінтів цього контролера.
@AllArgsConstructor
@RestController
@RequestMapping("/plugins")
public class PluginsController {
    PluginService pluginService;
    // Ендпоінт, який перевіряє, чи сервер "живий" (активний)
    @GetMapping("/is-alive")
    private ResponseEntity<Void> isAlive() {
        return ResponseEntity.ok().build(); //200
    }


    // Ендпоінт, який повертає список усіх назв плагінів.
    @GetMapping("/name-list")
    private ResponseEntity<List<String>> getAllPluginsName() {
        return ResponseEntity.ok(pluginService.getAllNames()); //200
    }
    // Ендпоінт, який валідує дії перед їх виконанням.
    @PostMapping("/validate")
    public ResponseEntity<String> validateTasks(@RequestBody String request) {
        try {
            JSONArray array = new JSONArray(request);
            pluginService.validateActions(array);
            // Відправляє HTTP 200 OK відповідь, якщо валідація пройшла успішно.
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Ендпоінт для виконання завдань/дій.
    @PostMapping("/execute")
    public ResponseEntity<String> executeTasks(@RequestBody String request) {
        try {
            JSONArray array = new JSONArray(request);
            pluginService.executeActions(array);
            // Відправляє HTTP 200 OK відповідь, якщо виконання пройшло успішно.
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
