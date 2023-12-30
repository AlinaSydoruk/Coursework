package com.example.trpzmacrosproject.executor;

import com.example.trpzmacrosproject.interpreters.exceptions.ActionsParsingException;
import com.example.trpzmacrosproject.scripts.ScriptActions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Getter
public class TaskExecutorImpl implements TaskExecutor {
    private final RestTemplate restTemplate;
    private final String serverAddress;
    private final String validateAddress;
    private final String executeAddress;
    private final String isAliveAddress;
    private final String getPluginNames;


    public List<String> getAllNames() throws RestClientException { //=====================

        // Виконання GET-запиту за допомогою RestTemplate
        ResponseEntity<List<String>> response = restTemplate.exchange(

                "http://" + serverAddress + "/" + getPluginNames, // URL для запиту

                HttpMethod.GET, // Тип HTTP-запиту (GET)

                null,  // Тіло HTTP-запиту (в даному випадку відсутнє)

                new ParameterizedTypeReference<>() {}
                 // Тип відповіді (List<String>)
        );
        // Повернення списку рядків отриманого відповіді
        return response.getBody();
    }

    @Override
    public void execute(ScriptActions scriptAction) throws ActionsParsingException {
        String json = scriptAction.getJsonActions();
        String url = "http://"+serverAddress+ "/"+ executeAddress;
        sendPostRequestWithJsonBody(json, url);
    }


    @Override
    public void validate(ScriptActions scriptAction) throws ActionsParsingException {
        //рядок JSON, представляючий масив дій
        String json = scriptAction.getJsonActions();

        String url = "http://"+serverAddress+ "/"+ validateAddress;
        sendPostRequestWithJsonBody(json, url);
    }
    // Перевірка, чи сервер живий
    @Override
    public boolean isAlive() {
        try{
        ResponseEntity<Void> response = restTemplate.exchange(
                "http://" + serverAddress + "/" + getPluginNames,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
        });
            // Перевірка, чи статус відповіді успішний (2xx)
        return response.getStatusCode().is2xxSuccessful();

        }catch (Exception e){
            return false;
        }
    }

    // Відправлення POST-запиту з JSON-тілом

    private void sendPostRequestWithJsonBody(String jsonBody, String url) throws ActionsParsingException{

        //HTTP-заголовки (HTTP headers) представляють собою метадані, які використовуються для передачі додаткової інформації
        // Створення заголовків для HTTP-запиту
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(jsonBody, headers), String.class);
        } catch (RestClientException e){
            throw new ActionsParsingException("Error with request: "+ e.getMessage(), e);
        }
    }
}
