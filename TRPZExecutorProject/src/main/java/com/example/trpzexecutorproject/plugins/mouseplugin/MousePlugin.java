package com.example.trpzexecutorproject.plugins.mouseplugin;

import com.example.trpzexecutorproject.exceptions.ActionExecutingException;
import com.example.trpzexecutorproject.plugins.Plugin;
import com.example.trpzexecutorproject.plugins.keyplugin.KeyExecutor;
import com.example.trpzexecutorproject.plugins.keyplugin.KeyValidator;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MousePlugin implements Plugin {
    private static final Logger logger = LoggerFactory.getLogger(MousePlugin.class);
    MouseExecutor mouseExecutor;
    MouseValidator mouseValidator;


    @Override
    public void validate(JSONObject jsonObject) {
        try {
            mouseValidator.validate(jsonObject);
        } catch (Exception e){
            throw new ActionExecutingException("Error in validation: " + e.getMessage(), e);
        }
    }

    @Override
    public void execute(JSONObject jsonObject) {
        try {
            mouseExecutor.execute(jsonObject);
        } catch (Exception e){
            throw new ActionExecutingException("Can't execute mouse action: " + e.getMessage(), e);
        }
    }

    @Override
    public String getPluginName() {
        return "MOUSE";
    }


}
