package com.example.trpzexecutorproject.service;

import com.example.trpzexecutorproject.exceptions.ActionExecutingException;
import com.example.trpzexecutorproject.exceptions.ActionParsingException;
import com.example.trpzexecutorproject.exceptions.ActionValidateException;
import com.example.trpzexecutorproject.plugins.Plugin;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Service
@AllArgsConstructor
public class PluginService {
    private static final Logger logger = LoggerFactory.getLogger(PluginService.class);
    private ApplicationContext applicationContext;
   

    public List<String> getAllNames() {
        List<String> result = new ArrayList<>();
        applicationContext.getBeansOfType(Plugin.class).forEach((s, p) -> result.add(p.getPluginName()));
        return result;
    }

    /**
     * Валідує спочатку об'єкт, потім його виконує
     * Виконує вс об'єкти, що є в списку     *
     * @param array список дій на виконання
     */
    public void executeActions(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            executeAction(object);
        }
    }

    public void executeAction(JSONObject object) {
        try {
            validateAction(object);
            String pluginName = object.getString("type");
            logger.info("Execute action with plugin: " + pluginName);
            findAndValidatePlugin(object).execute(object);
        } catch (Exception e) {
            logger.error(String.format("Error in action executing: %s", e.getMessage()));
            throw new ActionExecutingException(String.format("Error in action executing: %s", e.getMessage()), e);
        }
    }


    public void validateActions(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            validateAction(array.getJSONObject(i));
        }
    }

    public void validateAction(JSONObject object) {
        Plugin plugin = findAndValidatePlugin(object);
        validateWithPlugin(plugin, object);
    }

    private Plugin findAndValidatePlugin(JSONObject object) {
        String type = object.getString("type");
        Optional<Plugin> pluginOpt = getPluginByName(type);
        if (pluginOpt.isEmpty()) {
            throw new ActionParsingException("There is no plugin with this type: " + type);
        }
        return pluginOpt.get();
    }

    private void validateWithPlugin(Plugin plugin, JSONObject object) {
        try {
            plugin.validate(object);
        } catch (Exception e) {
            throw new ActionValidateException(String.format("Validation error in plugin %s: %s",
                    plugin.getPluginName(), e.getMessage()), e);
        }
    }

    private Optional<Plugin> getPluginByName(String name) {
        return applicationContext.getBeansOfType(Plugin.class)
                .values()
                .stream()
                .filter(o -> o.getPluginName().equals(name.toUpperCase(Locale.ROOT)))
                .findFirst();
    }

}
