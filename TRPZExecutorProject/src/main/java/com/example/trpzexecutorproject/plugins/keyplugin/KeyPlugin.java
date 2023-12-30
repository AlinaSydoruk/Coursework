package com.example.trpzexecutorproject.plugins.keyplugin;

import com.example.trpzexecutorproject.exceptions.ActionExecutingException;
import com.example.trpzexecutorproject.exceptions.ActionValidateException;
import com.example.trpzexecutorproject.plugins.Plugin;
import com.example.trpzexecutorproject.service.PluginService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
//Concrete Command
public class KeyPlugin implements Plugin {
    private static final Logger logger = LoggerFactory.getLogger(KeyPlugin.class);
    KeyExecutor keyExecutor;
    KeyValidator validator;

    @Override
    public void validate(JSONObject jsonObject) {
        validator.validate(jsonObject);
    }

    @Override
    public void execute(JSONObject jsonObject) {
        String keys = jsonObject.getString("keys");
        int keyAmount = keys.split("\\s").length;;
        logger.info(String.format("Pressed %d keys: %s", keyAmount, keys));
        try {
            keyExecutor.execute(jsonObject);
        } catch (Exception e){
            throw new ActionExecutingException("Can't execute action: " + e.getMessage(), e);
        }
    }

    @Override
    public String getPluginName() {
        return "KEYBOARD";
    }


}
