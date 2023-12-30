package com.example.trpzexecutorproject.plugins.gmailplugin;


import com.example.trpzexecutorproject.exceptions.ActionExecutingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.*;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class MailSender implements MailFacade {
    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);
    Properties mailProperties;

    @Autowired
    public MailSender(@Qualifier("mailProperties") Properties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Override
    public void sendMessage(String toEmail, String subject, String body){
        String fromEmail = mailProperties.getProperty("from");
        Session session = Session.getInstance(mailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, mailProperties.getProperty("password"));
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            // від кого
            message.setFrom(new InternetAddress(fromEmail));
            // кому
            message.addRecipients(Message.RecipientType.TO, toEmail);
            //  заголовок письма
            message.setSubject(subject);
            //  текст
            message.setText(body);
            // відправляємо повідомлення
            Transport.send(message);

            logger.info("Mail was successfully sent: "+ toEmail);

        } catch (Exception e){
            throw new ActionExecutingException("Can't execute Email action: "+ e.getMessage(), e);
        }

    }

}


