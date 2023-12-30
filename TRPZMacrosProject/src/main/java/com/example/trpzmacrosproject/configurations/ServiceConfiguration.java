package com.example.trpzmacrosproject.configurations;

import com.example.trpzmacrosproject.executor.TaskExecutorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
// @PropertySource вказує на файл властивостей, який Spring використовуватиме для отримання конфігураційних значень.
@PropertySource("classpath:config.properties")
public class ServiceConfiguration {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    // Для створення цього біна використовуються значення, визначені в файлі властивостей config.properties
    // та ін'єктуються через анотацію @Value.
    @Bean
    public TaskExecutorImpl getTaskExecutorImpl(RestTemplate restTemplate,
                                                @Value("${pluginsServerDomain}") String  serverAddress,  // Адреса сервера плагінів
                                                @Value("${validatePath}") String validateAddress, // Шлях для валідації
                                                @Value("${executePath}") String executeAddress, // Шлях для виконання
                                                @Value("${getPluginsNamePath}") String getPluginNames,// Шлях для отримання імен плагінів
                                                @Value("${isAlivePath}") String isAlivePath){  // Шлях для перевірки, чи сервер активний
      return new TaskExecutorImpl(restTemplate, serverAddress, validateAddress, executeAddress, getPluginNames, isAlivePath);
    }
}
