package com.example.trpzmacrosproject;

import com.example.trpzmacrosproject.interpreters.readers.AbstractFileReader;
import com.example.trpzmacrosproject.interpreters.ScriptCreator;
import com.example.trpzmacrosproject.executor.TaskExecutorImpl;
import com.example.trpzmacrosproject.scripts.Script;
import com.example.trpzmacrosproject.services.ScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class TrpzMacrosProjectApplication {
    private static final Logger logger = LoggerFactory.getLogger(TrpzMacrosProjectApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TrpzMacrosProjectApplication.class, args);
        //Перевірка, що другий сервіс живий
        TaskExecutorImpl executor = context.getBean(TaskExecutorImpl.class);
        if (!executor.isAlive()){
            logger.error("Service doesn't online: " + executor.getServerAddress());
            context.close();
            System.exit(0);
        }

        //Прочитати макроси з файлів
        AbstractFileReader abstractFileReader = context.getBean(AbstractFileReader.class);
        List<File> allScripts = null;
        try {
            allScripts = abstractFileReader.getAllScripts();
        } catch (IOException e) {
            logger.error("ERROR WHILE GETTING SCRIPTS: " + e.getMessage());
            context.close();
            System.exit(0);

        }
        if (allScripts == null || allScripts.size()==0){
            logger.error("READER DOESN'T FIND ANY SCRIPTS. TURNING OFF.");
            context.close();
            System.exit(0);
        }

        //Запарсити прочитані файли у скрипти
        ScriptCreator scriptCreator = context.getBean(ScriptCreator.class);
        List<Script> scripts = new LinkedList<>();

        try {
            for (File file : allScripts) {
              scripts.add(scriptCreator.getScript(file));
            }
        }catch (Exception e) {
            logger.error("ERROR WHILE PARSING SCRIPTS: " + e.getMessage());
        }

        //Додати скрипти на виконання
        ScriptService scriptService = context.getBean(ScriptService.class);
        for (Script script : scripts){
            scriptService.addScriptToHolder(script);
        }

    }

}
