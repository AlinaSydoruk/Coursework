package com.example.trpzmacrosproject.configurations;

import com.example.trpzmacrosproject.interpreters.readers.AbstractFileReader;
import com.example.trpzmacrosproject.interpreters.readers.AllFileReader;
import com.example.trpzmacrosproject.interpreters.readers.SelectivelyFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class ScriptsConfiguration {
    @Bean
    public AbstractFileReader getFileReader(@Value("${scriptsFolder}") String path,  // Шлях до папки зі скриптами
                                                        @Value("${readAll}") boolean readAll,// Чи треба читати всі файли
                                                        @Value("${scriptsName}") String names){// Імена конкретних файлів для читання
        return readAll ? new AllFileReader(path): new SelectivelyFileReader(path, names);
    }
}


    /*Логіка методу визначає, який тип AbstractFileReader створити на основі параметра readAll:

        Якщо readAll є true, тоді метод повертає екземпляр AllFileReader, який, ймовірно, читатиме всі файли у вказаній директорії path.
        Якщо readAll є false, тоді повертається екземпляр SelectivelyFileReader, який читатиме лише файли з іменами, визначеними в names.
        Цей підхід дозволяє змінювати поведінку додатку без зміни його коду, просто змінюючи значення у файлі властивостей.*/