package com.example.trpzmacrosproject.interpreters.readers;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SelectivelyFileReader extends AbstractFileReader {
    String path;
    String names;
    // Метод для отримання списку файлів, вибраних за іменами
    public List<File> getAllScripts() throws IOException {
        return Files.walk(Paths.get(path))// Перебір всіх файлів та папок вказаного шляху
                .filter(Files::isRegularFile)// Відфільтрувати лише звичайні файли (не папки)
                .map(Path::toFile)// Перетворити шляхи в об'єкти типу File
                .filter( f -> isInList(f.getName())) // Відфільтрувати файли за іменами
                .collect(Collectors.toList());  // Зібрати результат у список
    }

    // Приватний метод для перевірки, чи ім'я файлу знаходиться в списку
    private boolean isInList(String name){

        // Розділити рядок імен на список імен та перевірити, чи міститься задане ім'я
        return Arrays.asList(names.split(", ")).contains(name);
    }
}
