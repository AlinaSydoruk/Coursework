package com.example.trpzmacrosproject.interpreters.readers;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AllFileReader extends AbstractFileReader {
    String path;
    // Метод для отримання списку всіх файлів з вказаного шляху з розширенням ".json"
    public List<File> getAllScripts() throws IOException {
        return Files.walk(Paths.get(path))// Перебір всіх файлів та папок вказаного шляху
                .filter(Files::isRegularFile) // Відфільтрувати лише звичайні файли (не папки)
                .filter(p -> p.toString().endsWith(".json")) // Відфільтрувати файли з розширенням ".json"
                .map(Path::toFile)// Перетворити шляхи в об'єкти типу File
                .collect(Collectors.toList()); // Зібрати результат у список
    }
}
