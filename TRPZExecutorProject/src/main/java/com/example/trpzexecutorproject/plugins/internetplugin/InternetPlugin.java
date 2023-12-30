package com.example.trpzexecutorproject.plugins.internetplugin;

import com.example.trpzexecutorproject.plugins.Plugin;
import lombok.AllArgsConstructor;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class InternetPlugin implements Plugin {
    InternetValidator internetValidator;
    InternetExecutor internetExecutor;

    @Override
    public void validate(JSONObject jsonObject) {
        String request = jsonObject.getString("request");
        switch (request.toUpperCase(Locale.ROOT)){
            case "GET", "POST", "PUT", "DELETE" -> internetValidator.sendRequestValidate(jsonObject);
            case "DOWNLOAD" -> internetValidator.downloadFileValidate(jsonObject);
            default -> throw new IllegalArgumentException("Illegal request: "+ request);
        }
    }

    @Override
    public void execute(JSONObject jsonObject) {
        String request = jsonObject.getString("request");
        switch (request.toUpperCase(Locale.ROOT)){
            case "GET", "POST", "PUT", "DELETE" -> internetExecutor.sendRequest(jsonObject, getMethodByName(request));
            case "DOWNLOAD" -> internetExecutor.downloadFile(jsonObject);
            default -> throw new IllegalArgumentException("Illegal request: "+ request);
        }
    }

    @Override
    public String getPluginName() {
        return "INTERNET";
    }

    private HttpMethod getMethodByName(String name){
       return switch (name.toUpperCase(Locale.ROOT)){
           case "GET" -> HttpMethod.GET;
           case "POST" -> HttpMethod.POST;
           case "PUT" -> HttpMethod.PUT;
           case "DELETE" -> HttpMethod.DELETE;
           default -> throw new IllegalArgumentException("Illegal name: "+ name);
       };
    }

}
