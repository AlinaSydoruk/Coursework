package com.example.trpzexecutorproject.plugins.gmailplugin;

import com.example.trpzexecutorproject.exceptions.ActionValidateException;
import com.example.trpzexecutorproject.plugins.Plugin;
import com.example.trpzexecutorproject.plugins.mouseplugin.MouseValidator;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class MailPlugin implements Plugin {
    MailFacade mailFacade;
    MailValidator mailValidator;

    //  проводить валідацію JSON-об'єкта, використовуючи mailValidator
    @Override
    public void validate(JSONObject jsonObject) {
        try{
            mailValidator.validate(jsonObject);
        } catch (Exception e){
           throw new ActionValidateException(String.format("Error in validating %s: %s", getPluginName(), e.getMessage()), e);
        }
    }
    //  відсилає електронні листи на адреси
    @Override
    public void execute(JSONObject jsonObject) {
        String subject = jsonObject.getString("subject");
        String body = jsonObject.getString("body");
        String[] addresses = jsonObject.getString("addresses").split("\\s");
        for (String  address : addresses){
            mailFacade.sendMessage(address, subject, body);
        }
    }

    @Override
    public String getPluginName() {
        return "MAIL";
    }
}
