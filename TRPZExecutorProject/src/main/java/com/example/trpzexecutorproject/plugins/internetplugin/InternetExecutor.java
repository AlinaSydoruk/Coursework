package com.example.trpzexecutorproject.plugins.internetplugin;

import com.example.trpzexecutorproject.exceptions.ActionExecutingException;
import com.example.trpzexecutorproject.utils.JSONConvertor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

@Component
public class InternetExecutor {
    private static final Logger logger = LoggerFactory.getLogger(InternetExecutor.class);

    public void downloadFile(JSONObject jsonObject) {
        String fileUrl = jsonObject.getString("url");
        String path = jsonObject.getString("path");
        String fileName = jsonObject.getString("name");
        try {
            URL url = new URL(fileUrl);
            Path destinationPath = Paths.get(path, fileName);

            try (InputStream in = url.openStream();
                 FileOutputStream fos = new FileOutputStream(destinationPath.toFile())) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                logger.info(String.format("File %s was successfully downloaded:  %s", fileName, destinationPath));
            }
        } catch (Exception e) {
            throw new ActionExecutingException(String.format("File %s wasn't downloaded: %s", fileName , e.getMessage()), e);
        }
    }

    public void sendRequest(JSONObject jsonObject, HttpMethod method) {
        try {
            String url = jsonObject.getString("url");

            Optional<String> jsonBodyOpt = jsonObject.has("jsonBody") ?
                    Optional.of(jsonObject.getString("jsonBody"))
                    : Optional.empty();

            Optional<Map<String, String>> headersOpt = jsonObject.has("headers")?
                    Optional.of(JSONConvertor.convertToStringMap(jsonObject.getJSONObject("headers")))
                    : Optional.empty();

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String>  response = restTemplate.exchange(url, method, getEntity(jsonBodyOpt, headersOpt), String.class);

            HttpStatusCode statusCode = response.getStatusCode();
            logger.info(String.format("%s request on url: %s send statusCode: %s", method, url, statusCode));
        } catch (Exception e) {
            throw new ActionExecutingException(String.format("Can't execute %s request: %s", method, e.getMessage()), e);
        }

    }

    private HttpEntity<String> getEntity(Optional<String> jsonBodyOpt, Optional<Map<String, String>> headersOpt){
        String body = jsonBodyOpt.orElse("");
        HttpHeaders headers = new HttpHeaders();
        headersOpt.ifPresent(headers::setAll);
       return new HttpEntity<>(body, headers);
    }


}




