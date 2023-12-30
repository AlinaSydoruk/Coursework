package com.example.trpzexecutorproject.plugins.gmailplugin;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MailValidator {
    // Вираз для перевірки правильності електронної адреси
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public void validate(JSONObject jsonObject) {
        String subject = jsonObject.getString("subject");
        String body = jsonObject.getString("body");
        // Перевірка, чи тема не є пустою
        if (subject.trim().isEmpty()){
            throw new IllegalArgumentException("Mail don't valid subject");
        }
        // Перевірка, чи тіло не є пустим
        if (body.trim().isEmpty()){
            throw new IllegalArgumentException("Mail don't valid body");
        }

        // Отримання рядку, який містить адреси електронної пошти, та розбивка його на окремі адреси
        String[] addresses = jsonObject.getString("addresses").split("\\s");
        for (String address : addresses) {
            // Створення об'єкта Matcher для порівняння адреси з виразом
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(address);

            // Якщо адреса не відповідає виразу, викидується виключення
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Mail don't valid: " + address);
            }
        }

    }

}

