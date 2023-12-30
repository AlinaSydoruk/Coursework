package com.example.trpzexecutorproject.plugins.internetplugin;

import com.example.trpzexecutorproject.exceptions.ActionValidateException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class InternetValidator {
    public void downloadFileValidate(JSONObject jsonObject) {
        try{
            String fileUrl = jsonObject.getString("url");
            String path = jsonObject.getString("path");
            URL url = new URL(fileUrl);
            Path destinationPath = Paths.get(path);
            if (!Files.exists(destinationPath)){
                throw new IllegalArgumentException("There is no this path: "+ destinationPath);
            }
        } catch (Exception e){
            throw new ActionValidateException("Validate Exception: "+ e.getMessage(), e);
        }
    }

    public void sendRequestValidate(JSONObject jsonObject){
        try{
            String fileUrl = jsonObject.getString("url");
            URL url = new URL(fileUrl);
        } catch (Exception e){
            throw new ActionValidateException("Validate Exception: "+ e.getMessage(), e);
        }
    }

}
