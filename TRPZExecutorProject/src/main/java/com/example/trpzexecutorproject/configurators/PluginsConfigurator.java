package com.example.trpzexecutorproject.configurators;

import com.example.trpzexecutorproject.plugins.consoleplugin.ConsoleExecutor;
import com.example.trpzexecutorproject.plugins.consoleplugin.ConsoleValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@Configuration
@PropertySource("classpath:gmailconfig.properties")
public class PluginsConfigurator {

    @Bean
    public Properties mailProperties(@Value("${from}") String from,
                                        @Value("${password}") String password,
                                        @Value("${host}")String host,
                                        @Value("${smtpPort}")String smtpPort){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("from", from);
        properties.put("password", password);
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        return properties;
    }


}
